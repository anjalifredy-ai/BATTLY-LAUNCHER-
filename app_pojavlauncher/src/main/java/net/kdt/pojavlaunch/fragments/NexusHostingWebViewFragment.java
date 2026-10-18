package net.kdt.pojavlaunch.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;

/** In-app browser for free server hosts (Aternos, Minehut, etc.). */
public class NexusHostingWebViewFragment extends Fragment {

    public static final String TAG = "NexusHostingWebViewFragment";
    public static final String ARG_TITLE = "title";
    public static final String ARG_URL = "url";

    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nexus_hosting_webview, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = "Hosting";
        String url = "https://aternos.org/";
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(ARG_TITLE)) title = args.getString(ARG_TITLE, title);
            if (args.containsKey(ARG_URL)) url = args.getString(ARG_URL, url);
        }

        TextView titleView = view.findViewById(R.id.nexus_hosting_title);
        titleView.setText(title);

        view.findViewById(R.id.nexus_hosting_back).setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack());

        ProgressBar progress = view.findViewById(R.id.nexus_hosting_progress);
        mWebView = view.findViewById(R.id.nexus_hosting_webview);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progress == null) return;
                if (newProgress >= 100) {
                    progress.setVisibility(View.GONE);
                } else {
                    progress.setVisibility(View.VISIBLE);
                    progress.setProgress(newProgress);
                }
            }
        });

        mWebView.loadUrl(url);
    }

    @Override
    public void onDestroyView() {
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroyView();
    }
}
