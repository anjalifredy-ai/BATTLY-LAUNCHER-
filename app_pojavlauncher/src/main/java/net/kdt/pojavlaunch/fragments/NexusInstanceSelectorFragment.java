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

/** Nexus instance selector – screenshot 2/10 layout + actions */
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
                        Tools.swapFragment(requireActivity(), NexusHomeFragment.class,
                                NexusHomeFragment.TAG, null);
                    } catch (Exception ignored) {}
                }
            });
        }
    }
}
