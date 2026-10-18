package net.kdt.pojavlaunch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;
import net.kdt.pojavlaunch.extra.ExtraConstants;
import net.kdt.pojavlaunch.extra.ExtraCore;
import net.kdt.pojavlaunch.value.launcherprofiles.LauncherProfiles;
import net.kdt.pojavlaunch.value.launcherprofiles.MinecraftProfile;

/** Nexus home – screenshot-matched UI + real PLAY / resources / servers. */
public class NexusHomeFragment extends Fragment {

    public static final String TAG = "NexusHomeFragment";

    private TextView mProfileName;
    private TextView mProfileVersion;
    private TextView mPlayVersionSub;
    private TextView mHeroVersion;
    private ImageView mFabricBadge;
    private boolean mLaunchStarting;

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
        mPlayVersionSub = view.findViewById(R.id.nexus_play_version_sub);
        mHeroVersion = view.findViewById(R.id.nexus_hero_version);
        mFabricBadge = view.findViewById(R.id.nexus_fabric_badge);

        View play = view.findViewById(R.id.nexus_play_button);
        if (play != null) play.setOnClickListener(v -> onPlayClicked());

        View edit = view.findViewById(R.id.nexus_edit_profile_btn);
        if (edit != null) edit.setOnClickListener(v -> openInstanceSelector());

        View profileCard = view.findViewById(R.id.nexus_selected_profile_card);
        if (profileCard != null) profileCard.setOnClickListener(v -> openInstanceSelector());

        View browser = view.findViewById(R.id.nexus_browser_resources_card);
        if (browser != null) {
            browser.setOnClickListener(v -> safeSwap(SearchModFragment.class, SearchModFragment.TAG,
                    "Mods / resource packs / shaders"));
        }

        View manager = view.findViewById(R.id.nexus_manager_card);
        if (manager != null) {
            manager.setOnClickListener(v -> {
                try {
                    Tools.swapFragment(requireActivity(), BattlyFileManagerFragment.class,
                            BattlyFileManagerFragment.TAG, null);
                } catch (Exception e) {
                    Toast.makeText(requireContext(), "Manager", Toast.LENGTH_SHORT).show();
                }
            });
        }

        View servers = view.findViewById(R.id.nexus_servers_card);
        if (servers != null) {
            servers.setOnClickListener(v ->
                    safeSwap(NexusServersFragment.class, NexusServersFragment.TAG, "Servers"));
        }

        View aternos = view.findViewById(R.id.nexus_home_aternos);
        if (aternos != null) {
            aternos.setOnClickListener(v -> openHosting("Aternos", "https://aternos.org/"));
        }

        View minehut = view.findViewById(R.id.nexus_home_minehut);
        if (minehut != null) {
            minehut.setOnClickListener(v -> openHosting("Minehut", "https://minehut.com/"));
        }

        refreshProfileUi();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshProfileUi();
    }

    private void openHosting(String title, String url) {
        Bundle args = new Bundle();
        args.putString(NexusHostingWebViewFragment.ARG_TITLE, title);
        args.putString(NexusHostingWebViewFragment.ARG_URL, url);
        Tools.swapFragment(requireActivity(), NexusHostingWebViewFragment.class,
                NexusHostingWebViewFragment.TAG, args);
    }

    private void safeSwap(Class<? extends Fragment> cls, String tag, String fallback) {
        try {
            Tools.swapFragment(requireActivity(), cls, tag, null);
        } catch (Exception e) {
            Toast.makeText(requireContext(), fallback, Toast.LENGTH_SHORT).show();
        }
    }

    private void onPlayClicked() {
        if (mLaunchStarting) return;
        mLaunchStarting = true;
        View play = getView() != null ? getView().findViewById(R.id.nexus_play_button) : null;
        if (play != null) play.setEnabled(false);
        ExtraCore.setValue(ExtraConstants.LAUNCH_GAME, true);
        if (getView() != null) {
            getView().postDelayed(() -> {
                mLaunchStarting = false;
                if (play != null) play.setEnabled(true);
            }, 2000);
        }
    }

    private void openInstanceSelector() {
        safeSwap(NexusInstanceSelectorFragment.class, NexusInstanceSelectorFragment.TAG, "Instances");
    }

    public void refreshProfileUi() {
        if (mProfileName == null) return;
        try {
            LauncherProfiles.load();
            MinecraftProfile profile = LauncherProfiles.getCurrentProfile();
            if (profile != null) {
                String name = profile.name != null && !profile.name.isEmpty()
                        ? profile.name : "Profile";
                String ver = profile.lastVersionId != null ? profile.lastVersionId : "Unknown";
                mProfileName.setText(name);
                if (mProfileVersion != null) mProfileVersion.setText(ver);
                if (mPlayVersionSub != null) mPlayVersionSub.setText(ver);
                if (mHeroVersion != null) mHeroVersion.setText(ver);
                if (mFabricBadge != null) {
                    String low = (ver + " " + name).toLowerCase();
                    mFabricBadge.setVisibility(View.VISIBLE);
                }
            } else {
                mProfileName.setText("Select profile");
                if (mProfileVersion != null) mProfileVersion.setText("Tap to choose instance");
                if (mPlayVersionSub != null) mPlayVersionSub.setText("—");
            }
        } catch (Exception e) {
            mProfileName.setText("Default");
            if (mProfileVersion != null) mProfileVersion.setText("—");
        }
    }
}
