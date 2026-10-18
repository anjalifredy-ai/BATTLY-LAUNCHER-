package net.kdt.pojavlaunch.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

/** Saved multiplayer servers + shortcuts to free hosting WebViews. */
public class NexusServersFragment extends Fragment {

    public static final String TAG = "NexusServersFragment";
    private static final String PREFS = "nexus_servers";
    private static final String KEY = "list";

    private LinearLayout mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_servers_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = view.findViewById(R.id.nexus_server_list);

        view.findViewById(R.id.nexus_servers_back).setOnClickListener(v ->
                Tools.backToMainMenu(requireActivity()));

        view.findViewById(R.id.nexus_add_server_btn).setOnClickListener(v -> showAddDialog());

        view.findViewById(R.id.nexus_open_aternos).setOnClickListener(v ->
                openHosting("Aternos", "https://aternos.org/"));

        view.findViewById(R.id.nexus_open_minehut).setOnClickListener(v ->
                openHosting("Minehut", "https://minehut.com/"));

        refreshList();
    }

    private void openHosting(String title, String url) {
        Bundle args = new Bundle();
        args.putString(NexusHostingWebViewFragment.ARG_TITLE, title);
        args.putString(NexusHostingWebViewFragment.ARG_URL, url);
        Tools.swapFragment(requireActivity(), NexusHostingWebViewFragment.class,
                NexusHostingWebViewFragment.TAG, args);
    }

    private void showAddDialog() {
        Context ctx = requireContext();
        LinearLayout box = new LinearLayout(ctx);
        box.setOrientation(LinearLayout.VERTICAL);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        box.setPadding(pad, pad, pad, pad);

        final EditText name = new EditText(ctx);
        name.setHint("Server name");
        box.addView(name);

        final EditText address = new EditText(ctx);
        address.setHint("Address (host or host:port)");
        address.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        box.addView(address);

        new AlertDialog.Builder(ctx)
                .setTitle("Add server")
                .setView(box)
                .setPositiveButton("Save", (d, w) -> {
                    String n = name.getText().toString().trim();
                    String a = address.getText().toString().trim();
                    if (n.isEmpty() || a.isEmpty()) {
                        Toast.makeText(ctx, "Name and address required", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    addServer(n, a);
                    refreshList();
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private SharedPreferences prefs() {
        return requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    private JSONArray load() {
        try {
            String raw = prefs().getString(KEY, "[]");
            return new JSONArray(raw);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private void save(JSONArray arr) {
        prefs().edit().putString(KEY, arr.toString()).apply();
    }

    private void addServer(String name, String address) {
        try {
            JSONArray arr = load();
            JSONObject o = new JSONObject();
            o.put("name", name);
            o.put("address", address);
            arr.put(o);
            save(arr);
        } catch (Exception ignored) {
        }
    }

    private void removeAt(int index) {
        JSONArray arr = load();
        JSONArray next = new JSONArray();
        for (int i = 0; i < arr.length(); i++) {
            if (i == index) continue;
            try {
                next.put(arr.get(i));
            } catch (Exception ignored) {
            }
        }
        save(next);
    }

    private void refreshList() {
        if (mList == null) return;
        mList.removeAllViews();
        JSONArray arr = load();
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        if (arr.length() == 0) {
            TextView empty = new TextView(requireContext());
            empty.setText("No servers yet. Add one or open Aternos / Minehut.");
            empty.setTextColor(0xFF9AA4B2);
            empty.setPadding(8, 24, 8, 24);
            mList.addView(empty);
            return;
        }
        for (int i = 0; i < arr.length(); i++) {
            final int index = i;
            try {
                JSONObject o = arr.getJSONObject(i);
                View row = inflater.inflate(R.layout.nexus_server_row, mList, false);
                TextView title = row.findViewById(R.id.nexus_server_row_name);
                TextView addr = row.findViewById(R.id.nexus_server_row_address);
                title.setText(o.optString("name", "Server"));
                addr.setText(o.optString("address", ""));
                row.findViewById(R.id.nexus_server_row_delete).setOnClickListener(v -> {
                    removeAt(index);
                    refreshList();
                });
                mList.addView(row);
            } catch (Exception ignored) {
            }
        }
    }
}
