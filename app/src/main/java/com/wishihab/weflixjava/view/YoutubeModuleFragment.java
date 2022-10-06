package com.wishihab.weflixjava.view;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.wishihab.weflixjava.R;
import com.wishihab.weflixjava.databinding.FragmentYoutubeModuleBinding;
import com.wishihab.weflixjava.util.general.DeviceUtil;


public class YoutubeModuleFragment extends BottomSheetDialogFragment {

    private FragmentYoutubeModuleBinding binding;
    private Listener listener;
    private static final String ARG_WHATSAPP = "whatsapp";
    private static final String ARG_STORIES = "stories";
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private String apiKey;
    private WebView youtubeWeb;

    public interface Listener{
        void onDismissedCall();
    }

    public static YoutubeModuleFragment newInstance(String param1) {
        YoutubeModuleFragment fragment = new YoutubeModuleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.TransparentBottomSheetDialogTheme);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final Context context = getActivity();
        dialog.setOnShowListener(di -> {
            if (!DeviceUtil.isPortraitOrientation(context)) {
                makeStateExpanded((BottomSheetDialog) di);
            }
        });

        return dialog;
    }

    private void makeStateExpanded(BottomSheetDialog dialog) {
        BottomSheetBehavior<FrameLayout> behaviour = dialog.getBehavior();
        behaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYoutubeModuleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener = (Listener) requireActivity();
        apiKey = "AIzaSyBNggAqYJVmKn-p7D_DrlWyX3Rc24fv0uI";

        youtubeWeb = (WebView)view.findViewById(R.id.webview_youtube);
        String fullUrl = "https://www.youtube.com/watch?v=" + mParam1;
        youtubeWeb.loadUrl(fullUrl);

//        FrameLayout youtubeView = (FrameLayout)view.findViewById(R.id.youtube_view);
//        YouTubePlayerSupportFragment ytPlayer = new YouTubePlayerSupportFragment();
//
//        ytPlayer.initialize(apiKey, new YouTubePlayer.OnInitializedListener(){
//
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(mParam1);
//                youTubePlayer.play();
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                //error
//            }
//        });

        binding.imgClose.setOnClickListener(v -> {
            listener.onDismissedCall();
            dismiss();
        });

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        listener.onDismissedCall();
        super.onDismiss(dialog);
    }
}
