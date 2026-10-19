package net.kdt.pojavlaunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;

/** Create-instance UI shell → existing profile editor / type select for real install. */
public class NexusCreateInstanceFragment extends Fragment {
    public static final String TAG = "NexusCreateInstanceFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_create_instance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        View.OnClickListener close = v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            } else {
                Tools.backToMainMenu(requireActivity());
            }
        };
        view.findViewById(R.id.nexus_create_back).setOnClickListener(close);
        view.findViewById(R.id.nexus_create_close).setOnClickListener(close);
        view.findViewById(R.id.nexus_create_cancel).setOnClickListener(close);

        view.findViewById(R.id.nexus_create_confirm).setOnClickListener(v -> {
            EditText name = view.findViewById(R.id.nexus_create_name);
            String n = name != null ? name.getText().toString().trim() : "";
            if (n.isEmpty()) {
                Toast.makeText(requireContext(), "Enter a name", Toast.LENGTH_SHORT).show();
                return;
            }
            // Hand off to existing working installer UI
            try {
                Tools.swapFragment(requireActivity(), ProfileTypeSelectFragment.class,
                        ProfileTypeSelectFragment.TAG, null);
            } catch (Exception e) {
                try {
                    Tools.swapFragment(requireActivity(), ProfileEditorFragment.class,
                            ProfileEditorFragment.TAG, null);
                } catch (Exception e2) {
                    Toast.makeText(requireContext(), "Open profile editor to finish setup", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
