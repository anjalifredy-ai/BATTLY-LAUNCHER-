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
import net.kdt.pojavlaunch.prefs.screens.LauncherPreferenceExperimentalFragment;
import net.kdt.pojavlaunch.prefs.screens.LauncherPreferenceJavaFragment;
import net.kdt.pojavlaunch.prefs.screens.LauncherPreferenceMiscellaneousFragment;
import net.kdt.pojavlaunch.prefs.screens.LauncherPreferenceVideoFragment;

/** Screenshot-style settings hub → real preference screens. */
public class NexusSettingsFragment extends Fragment {
    public static final String TAG = "NexusSettingsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_settings_hub, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.nexus_settings_back).setOnClickListener(v ->
                Tools.backToMainMenu(requireActivity()));

        view.findViewById(R.id.nexus_set_java).setOnClickListener(v ->
                open(LauncherPreferenceJavaFragment.class, "Java"));
        view.findViewById(R.id.nexus_set_misc).setOnClickListener(v ->
                open(LauncherPreferenceMiscellaneousFragment.class, "Misc"));
        view.findViewById(R.id.nexus_set_video).setOnClickListener(v ->
                open(LauncherPreferenceVideoFragment.class, "Video"));
        view.findViewById(R.id.nexus_set_experimental).setOnClickListener(v ->
                open(LauncherPreferenceExperimentalFragment.class, "Experimental"));
    }

    private void open(Class<? extends Fragment> cls, String label) {
        try {
            Tools.swapFragment(requireActivity(), cls, label, null);
        } catch (Exception e) {
            Toast.makeText(requireContext(), label, Toast.LENGTH_SHORT).show();
        }
    }
}
