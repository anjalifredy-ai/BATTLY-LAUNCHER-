package net.kdt.pojavlaunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;

public class NexusNewInstanceTypeFragment extends Fragment {
    public static final String TAG = "NexusNewInstanceTypeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_new_instance_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.nexus_newtype_close).setOnClickListener(v -> pop());

        view.findViewById(R.id.nexus_type_normal).setOnClickListener(v -> {
            try {
                Tools.swapFragment(requireActivity(), NexusCreateInstanceFragment.class,
                        NexusCreateInstanceFragment.TAG, null);
            } catch (Exception e) {
                openProfileEditor();
            }
        });

        view.findViewById(R.id.nexus_type_modpack).setOnClickListener(v -> {
            try {
                Tools.swapFragment(requireActivity(), SearchModFragment.class, SearchModFragment.TAG, null);
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Modpacks", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openProfileEditor() {
        try {
            Tools.swapFragment(requireActivity(), ProfileTypeSelectFragment.class,
                    ProfileTypeSelectFragment.TAG, null);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Create instance", Toast.LENGTH_SHORT).show();
        }
    }

    private void pop() {
        if (getParentFragmentManager().getBackStackEntryCount() > 0) {
            getParentFragmentManager().popBackStack();
        } else {
            Tools.backToMainMenu(requireActivity());
        }
    }
}
