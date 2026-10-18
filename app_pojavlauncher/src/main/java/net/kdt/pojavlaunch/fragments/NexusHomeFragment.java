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
 * Nexus Launcher home – layout matches reference screenshots
 * (dark navy, hero banner, selected profile, PLAY, Browser Resources, Manager).
 *
 * Enabled by changing LauncherActivity root fragment to this class
 * (see NEXUS_LAUNCHER_ACTIVITY_PATCH.md).
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
                // TODO: call same launch path as MainMenuFragment play button
                Toast.makeText(requireContext(),
                        "Nexus PLAY – connect to existing launch pipeline",
                        Toast.LENGTH_SHORT).show();
            });
        }
    }

    /** Call from LauncherActivity.refreshHomeProfileUi when ready */
    public void refreshProfileUi() {
        // Bind selected profile name / version here
    }
}
