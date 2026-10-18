package net.kdt.pojavlaunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;

/**
 * Nexus Launcher instance / profile selector.
 * Layout: res/layout/nexus_instance_selector.xml
 * Matches screenshot 2/10 (left profile summary + All Instances list + ACTIVE badge).
 *
 * TODO wiring:
 * - Populate instances from LauncherProfiles / MinecraftLauncherProfiles
 * - Mark current selected instance with ACTIVE badge
 * - Create New Instance -> ProfileEditorFragment or existing create flow
 * - Edit buttons -> ProfileEditorFragment
 * - Selecting an instance updates the home Selected Profile card and Play target
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
        // Bind real profile list here
    }
}
