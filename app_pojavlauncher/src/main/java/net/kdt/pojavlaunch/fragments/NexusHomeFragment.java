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

/**
 * Nexus Launcher home screen fragment.
 * Layout: res/layout/nexus_home_screen.xml
 * Theme: dark navy + blue glass (matches reference screenshots).
 *
 * TODO wiring:
 * - Bind selected profile name / version from LauncherProfiles
 * - PLAY button -> existing launch pipeline (same as current home Play)
 * - Browser Resources / Manager cards -> existing mod / resource fragments
 * - Account area -> mcAccountSpinner / Microsoft auth
 * - Instance dropdown -> NexusInstanceSelectorFragment or existing profile editor
 */
public class NexusHomeFragment extends Fragment {

    public static final String TAG = "NexusHomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View play = view.findViewById(R.id.nexus_play_button);
        if (play != null) {
            play.setOnClickListener(v -> {
                // Placeholder: will call the same launch path used by the current home Play button
                Toast.makeText(requireContext(), "Nexus Play – wire to launch pipeline", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
