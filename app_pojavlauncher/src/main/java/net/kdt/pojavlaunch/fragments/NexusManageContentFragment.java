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
import net.kdt.pojavlaunch.value.launcherprofiles.LauncherProfiles;
import net.kdt.pojavlaunch.value.launcherprofiles.MinecraftProfile;

/** Manage Content shell (Mods / RP / Shaders) → real manager + mod library. */
public class NexusManageContentFragment extends Fragment {
    public static final String TAG = "NexusManageContentFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_manage_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView sub = view.findViewById(R.id.nexus_manage_subtitle);
        try {
            LauncherProfiles.load();
            MinecraftProfile p = LauncherProfiles.getCurrentProfile();
            if (p != null && sub != null) {
                String name = p.name != null ? p.name : "Profile";
                String ver = p.lastVersionId != null ? p.lastVersionId : "";
                sub.setText(name + (ver.isEmpty() ? "" : " · " + ver));
            }
        } catch (Exception ignored) {}

        view.findViewById(R.id.nexus_manage_back).setOnClickListener(v ->
                Tools.backToMainMenu(requireActivity()));

        view.findViewById(R.id.nexus_manage_browse).setOnClickListener(v -> openLibrary());
        view.findViewById(R.id.nexus_manage_open_library).setOnClickListener(v -> openLibrary());

        view.findViewById(R.id.nexus_manage_open_full).setOnClickListener(v -> {
            try {
                Tools.swapFragment(requireActivity(), BattlyFileManagerFragment.class,
                        BattlyFileManagerFragment.TAG, null);
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Manager", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openLibrary() {
        try {
            Tools.swapFragment(requireActivity(), SearchModFragment.class, SearchModFragment.TAG, null);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Mod library", Toast.LENGTH_SHORT).show();
        }
    }
}
