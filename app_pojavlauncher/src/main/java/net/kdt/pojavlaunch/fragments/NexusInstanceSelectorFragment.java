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

/**
 * Instance / profile selector – matches screenshot 2/10.
 */
public class NexusInstanceSelectorFragment extends Fragment {

    public static final String TAG = "NexusInstanceSelectorFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_instance_selector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create New Instance – open existing profile editor flow when possible
        View createBtn = view.findViewWithTag("nexus_create_instance");
        // Fallback: find by walking or use layout ids when added
        view.setOnClickListener(null);

        // Temporary: long-press root to go to ProfileTypeSelectFragment
        view.setOnLongClickListener(v -> {
            try {
                Tools.swapFragment(requireActivity(), ProfileTypeSelectFragment.class,
                        ProfileTypeSelectFragment.TAG, null);
            } catch (Exception e) {
                try {
                    Tools.swapFragment(requireActivity(), ProfileEditorFragment.class,
                            ProfileEditorFragment.TAG, null);
                } catch (Exception e2) {
                    Toast.makeText(requireContext(), "Create instance", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        });
    }
}
