package net.kdt.pojavlaunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;

/**
 * Nexus home screen – screenshot layout + click wiring stubs.
 * Enable via LauncherActivity: MainMenuFragment.class -> NexusHomeFragment.class
 * See NEXUS_LAUNCHER_ACTIVITY_PATCH.md
 */
public class NexusHomeFragment extends Fragment {

    public static final String TAG = "NexusHomeFragment";

    private TextView mProfileName;
    private TextView mProfileVersion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProfileName = view.findViewById(R.id.nexus_selected_profile_name);
        mProfileVersion = view.findViewById(R.id.nexus_selected_profile_version);

        View play = view.findViewById(R.id.nexus_play_button);
        if (play != null) {
            play.setOnClickListener(v -> onPlayClicked());
        }

        View edit = view.findViewById(R.id.nexus_edit_profile_btn);
        if (edit != null) {
            edit.setOnClickListener(v -> openInstanceSelector());
        }

        View profileCard = view.findViewById(R.id.nexus_selected_profile_card);
        if (profileCard != null) {
            profileCard.setOnClickListener(v -> openInstanceSelector());
        }

        View browser = view.findViewById(R.id.nexus_browser_resources_card);
        if (browser != null) {
            browser.setOnClickListener(v -> {
                try {
                    Tools.swapFragment(requireActivity(), SearchModFragment.class, SearchModFragment.TAG, null);
                } catch (Exception e) {
                    Toast.makeText(requireContext(), "Open mods browser", Toast.LENGTH_SHORT).show();
                }
            });
        }

        View manager = view.findViewById(R.id.nexus_manager_card);
        if (manager != null) {
            manager.setOnClickListener(v -> {
                try {
                    Tools.swapFragment(requireActivity(), BattlyFileManagerFragment.class, BattlyFileManagerFragment.TAG, null);
                } catch (Exception e) {
                    Toast.makeText(requireContext(), "Open manager", Toast.LENGTH_SHORT).show();
                }
            });
        }

        refreshProfileUi();
    }

    private void onPlayClicked() {
        // Wire to the same launch entry point MainMenuFragment uses.
        // Until then, show feedback so the control is not dead.
        Toast.makeText(requireContext(),
                "PLAY – connect to MainMenuFragment launch path",
                Toast.LENGTH_SHORT).show();
    }

    private void openInstanceSelector() {
        try {
            Tools.swapFragment(requireActivity(), NexusInstanceSelectorFragment.class,
                    NexusInstanceSelectorFragment.TAG, null);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Open instances", Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshProfileUi() {
        // TODO: read current profile from LauncherProfiles and set:
        // mProfileName / mProfileVersion
        if (mProfileName != null) {
            // placeholder until data binding
        }
    }
}
