package net.kdt.pojavlaunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;
import net.kdt.pojavlaunch.prefs.LauncherPreferences;
import net.kdt.pojavlaunch.value.launcherprofiles.LauncherProfiles;
import net.kdt.pojavlaunch.value.launcherprofiles.MinecraftProfile;

import java.util.Map;

/** Screenshot-style dual panel: current profile + all instances with ACTIVE badge. */
public class NexusInstanceSelectorFragment extends Fragment {

    public static final String TAG = "NexusInstanceSelectorFragment";

    private LinearLayout mList;
    private TextView mCount;
    private TextView mCurrentName;
    private TextView mCurrentVersion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_instance_selector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mList = view.findViewById(R.id.nexus_instance_list);
        mCount = view.findViewById(R.id.nexus_instance_count);
        mCurrentName = view.findViewById(R.id.nexus_current_profile_name);
        mCurrentVersion = view.findViewById(R.id.nexus_current_profile_version);

        View create = view.findViewById(R.id.nexus_create_instance_btn);
        if (create != null) {
            create.setOnClickListener(v -> {
                try {
                    Tools.swapFragment(requireActivity(), ProfileTypeSelectFragment.class,
                            ProfileTypeSelectFragment.TAG, null);
                } catch (Exception e) {
                    try {
                        Tools.swapFragment(requireActivity(), ProfileEditorFragment.class,
                                ProfileEditorFragment.TAG, null);
                    } catch (Exception e2) {
                        Toast.makeText(requireContext(), "Create New Instance", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        View close = view.findViewById(R.id.nexus_instance_close);
        if (close != null) {
            close.setOnClickListener(v -> {
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                } else {
                    try {
                        Tools.backToMainMenu(requireActivity());
                    } catch (Exception ignored) {}
                }
            });
        }

        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        if (mList == null) return;
        mList.removeAllViews();
        try {
            LauncherProfiles.load();
            if (LauncherProfiles.mainProfileJson == null
                    || LauncherProfiles.mainProfileJson.profiles == null) {
                if (mCount != null) mCount.setText("0 instances");
                return;
            }

            String currentKey = LauncherPreferences.DEFAULT_PREF.getString(
                    LauncherPreferences.PREF_KEY_CURRENT_PROFILE, "");
            MinecraftProfile current = LauncherProfiles.getCurrentProfile();
            if (current != null && mCurrentName != null) {
                String n = current.name != null && !current.name.isEmpty() ? current.name : currentKey;
                mCurrentName.setText(n);
                if (mCurrentVersion != null) {
                    mCurrentVersion.setText(current.lastVersionId != null ? current.lastVersionId : "");
                }
            }

            Map<String, MinecraftProfile> profiles = LauncherProfiles.mainProfileJson.profiles;
            if (mCount != null) {
                mCount.setText(profiles.size() + (profiles.size() == 1 ? " instance" : " instances"));
            }

            LayoutInflater inflater = LayoutInflater.from(requireContext());
            for (Map.Entry<String, MinecraftProfile> entry : profiles.entrySet()) {
                final String key = entry.getKey();
                MinecraftProfile p = entry.getValue();
                if (p == null) continue;

                View row = inflater.inflate(R.layout.nexus_instance_row, mList, false);
                TextView name = row.findViewById(R.id.nexus_row_name);
                TextView ver = row.findViewById(R.id.nexus_row_version);
                TextView active = row.findViewById(R.id.nexus_row_active);

                String display = p.name != null && !p.name.isEmpty() ? p.name : key;
                name.setText(display);
                ver.setText(p.lastVersionId != null ? p.lastVersionId : "Unknown");

                boolean isActive = key.equals(currentKey);
                if (active != null) {
                    active.setVisibility(isActive ? View.VISIBLE : View.GONE);
                }
                if (isActive) {
                    row.setBackgroundResource(R.drawable.nexus_card_elevated_bg);
                }

                row.setOnClickListener(v -> selectProfile(key));

                View edit = row.findViewById(R.id.nexus_row_edit);
                if (edit != null) {
                    edit.setOnClickListener(v -> {
                        try {
                            Tools.swapFragment(requireActivity(), ProfileEditorFragment.class,
                                    ProfileEditorFragment.TAG, null);
                        } catch (Exception e) {
                            Toast.makeText(requireContext(), "Edit", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                mList.addView(row);
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Could not load profiles", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectProfile(String key) {
        try {
            LauncherPreferences.DEFAULT_PREF.edit()
                    .putString(LauncherPreferences.PREF_KEY_CURRENT_PROFILE, key)
                    .apply();
            refresh();
            Toast.makeText(requireContext(), "Profile selected", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Select failed", Toast.LENGTH_SHORT).show();
        }
    }
}
