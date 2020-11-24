package com.example.batterymonitor.util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batterymonitor.MainActivity;
import com.example.batterymonitor.R;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


import static android.content.Context.WINDOW_SERVICE;

public class Utils {
    public static final int REQUEST_PERMISSION_CODE = 0;
    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[] {
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.PROCESS_OUTGOING_CALLS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                REQUEST_PERMISSION_CODE);
    }

    public static boolean checkPermissionFromDevice(Context context) {
        int read_external_storage_result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int write_external_storage_result = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        return record_audio_result == PackageManager.PERMISSION_GRANTED
                && read_external_storage_result == PackageManager.PERMISSION_GRANTED
                && write_external_storage_result == PackageManager.PERMISSION_GRANTED;
    }
//
//    public static void showPopUpGoPremium(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_go_premium_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.findViewById(R.id.continueBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String appPackageName = "thanhletranngoc.calculator.pro"; // getPackageName() from Context or Activity object
//                try {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                }
//            }
//        });
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//
//    public static void showWelcomeLayout(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_welcome_layout, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//        dialog.findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//                showAcceptResponsibilityDialog(context);
//            }
//        });
//
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//
//    public static void showAcceptResponsibilityDialog(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_accept_responsibility, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//        dialog.findViewById(R.id.agreeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//                showGrantPermissionDialog(context);
//            }
//        });
//
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//
//    public static void showGrantPermissionDialog(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_infor_permission, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//        dialog.findViewById(R.id.grantBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                requestPermission((Activity) context);
//                dialog.cancel();
//                SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//                sharePrefHelper.saveFirstInstallFlag(false);
//            }
//        });
//
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//
//    public static void showPopUpRatingReview(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_rate_review, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.rateBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                PackageManager packageManager = context.getPackageManager();
//                final String appPackageName = context.getPackageName();
//                try {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                }
//            }
//        });
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.findViewById(R.id.hideBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//
//    public static void showAudioFormatDialog(final Context context, final TextView textView) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_audio_format_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        final SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//        RadioGroup rdg = dialog.findViewById(R.id.radioGroup);
//
//        if(sharePrefHelper.getAudioFormat() != null) {
//            for (int i = 0; i < rdg.getChildCount(); i++) {
//                if (((RadioButton) rdg.getChildAt(i)).getText().equals(sharePrefHelper.getAudioFormat())) {
//                    rdg.check(rdg.getChildAt(i).getId());
//                }
//            }
//        }
//        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                sharePrefHelper.saveAudioFormat(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                textView.setText(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showSampleRateDialog(final Context context, final TextView textView) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_sample_rate_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        final SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//        RadioGroup rdg = dialog.findViewById(R.id.radioGroup);
//
//        if(sharePrefHelper.getSampleRate() != null) {
//            for (int i = 0; i < rdg.getChildCount(); i++) {
//                if (((RadioButton) rdg.getChildAt(i)).getText().equals(sharePrefHelper.getSampleRate())) {
//                    rdg.check(rdg.getChildAt(i).getId());
//                }
//            }
//        }
//        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                sharePrefHelper.saveSampleRate(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                textView.setText(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showRecordingSourceDialog(final Context context, final TextView textView) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_recording_source_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//
//        final SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//        RadioGroup rdg = dialog.findViewById(R.id.radioGroup);
//
//        if(sharePrefHelper.getRecordingSource() != null) {
//            for (int i = 0; i < rdg.getChildCount(); i++) {
//                if (((RadioButton) rdg.getChildAt(i)).getText().equals(sharePrefHelper.getRecordingSource())) {
//                    rdg.check(rdg.getChildAt(i).getId());
//                }
//            }
//        }
//        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                sharePrefHelper.saveRecordingSource(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                textView.setText(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showDeletingTimeDialog(final Context context, final TextView textView) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_deleting_time_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
////                context.startActivity(new Intent(context, MainActivity.class));
////                ((Activity) context).finish();
//            }
//        });
//
//        final SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//        RadioGroup rdg = dialog.findViewById(R.id.radioGroup);
//
//        if(sharePrefHelper.getDeletingTime() != null) {
//            for (int i = 0; i < rdg.getChildCount(); i++) {
//                if (((RadioButton) rdg.getChildAt(i)).getText().equals(sharePrefHelper.getDeletingTime())) {
//                    rdg.check(rdg.getChildAt(i).getId());
//                }
//            }
//        }
//        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                sharePrefHelper.saveDeletingTime(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                textView.setText(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                FileHelper.getInstance().findFileAndDelete();
//
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showRecordingSettingsDialog(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_recording_settings_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//        final SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        SwitchCompat recordIncomingSwitch = dialog.findViewById(R.id.recordIncomingSwitch);
//        SwitchCompat recordOutgoingSwitch = dialog.findViewById(R.id.recordOutgoingSwitch);
//        SwitchCompat autoDetectSwitch = dialog.findViewById(R.id.autoDetectSwitch);
//        recordIncomingSwitch.setChecked(sharePrefHelper.isRecordIncoming());
//        recordOutgoingSwitch.setChecked(sharePrefHelper.isRecordOutgoing());
//        autoDetectSwitch.setChecked(sharePrefHelper.isAutoDetect());
//
//        recordIncomingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                sharePrefHelper.saveRecordIncoming(b);
//            }
//        });
//        recordOutgoingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                sharePrefHelper.saveRecordOutgoing(b);
//            }
//        });
//        autoDetectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                sharePrefHelper.saveAutoDetect(b);
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showLanguageDialog(final Context context, final TextView textView) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_language_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        final SharePrefHelper sharePrefHelper = SharePrefHelper.getInstance(context);
//        RadioGroup rdg = dialog.findViewById(R.id.radioGroup);
//
//        if(sharePrefHelper.getLanguage() != null) {
//            for (int i = 0; i < rdg.getChildCount(); i++) {
//                if (((RadioButton) rdg.getChildAt(i)).getText().equals(sharePrefHelper.getLanguage())) {
//                    rdg.check(rdg.getChildAt(i).getId());
//                }
//            }
//        }
//        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                sharePrefHelper.saveLanguage(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                textView.setText(((RadioButton) dialog.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
//                setAppLocale(context);
//
//                context.startActivity(new Intent(context, MainActivity.class));
//                ((Activity)context).finish();
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showAboutDialog(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_about_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showPrivacyPolicyDialog(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_policy_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
//    }
//
//    public static void showTermOfUseDialog(final Context context) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View customView = layoutInflater.inflate(R.layout.custom_term_dialog, null);
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(customView);
//
//        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
//    }

    public static void setAppLocale(Context context){
        String localeCode;
        String curLang = SharePrefHelper.getInstance(context).getLanguage();
        if (curLang == null || curLang.contains("English"))
            localeCode = "en";
        else if (curLang.contains("Spanish"))
            localeCode = "es";
        else if (curLang.contains("Russian"))
            localeCode = "ru";
        else if (curLang.contains("Portugal"))
            localeCode = "pt";
        else
            localeCode = "vi";

        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
//            config.setLocale(new Locale(localeCode.toLowerCase()));
//        } else {
//            config.locale = new Locale(localeCode.toLowerCase());
//        }
        Locale.setDefault(new Locale(localeCode.toLowerCase()));
        config.setLocale(new Locale(localeCode.toLowerCase()));

        resources.updateConfiguration(config, dm);
    }


}
