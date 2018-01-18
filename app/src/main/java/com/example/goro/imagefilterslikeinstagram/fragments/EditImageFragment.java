package com.example.goro.imagefilterslikeinstagram.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.goro.imagefilterslikeinstagram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditImageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditImageFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private EditImageFragmentListener listener;

    @BindView(R.id.seekbar_brightness)
    SeekBar seekbarBrightness;

    @BindView(R.id.seekbar_contrast)
    SeekBar seekbarContrast;

    @BindView(R.id.seekbar_saturation)
    SeekBar seekbarSaturation;

    public void setListener(EditImageFragmentListener listener) {
        this.listener = listener;
    }

    public EditImageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_image, container, false);

        ButterKnife.bind(this, view);

        seekbarBrightness.setMax(200);
        seekbarBrightness.setProgress(100);

        seekbarContrast.setMax(20);
        seekbarContrast.setProgress(0);

        seekbarSaturation.setMax(30);
        seekbarSaturation.setProgress(10);

        seekbarBrightness.setOnSeekBarChangeListener(this);
        seekbarContrast.setOnSeekBarChangeListener(this);
        seekbarSaturation.setOnSeekBarChangeListener(this);

        return view;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (listener != null) {
            if (seekBar.getId() == R.id.seekbar_brightness) {
                listener.onBrightnessChanged(i - 100);
            }
            if (seekBar.getId() == R.id.seekbar_contrast) {
                i += 10;
                float floatVal = .10f * i;
                listener.onContrastChanged(floatVal);
            }
            if (seekBar.getId() == R.id.seekbar_saturation) {
                float floatVal = .10f * i;
                listener.onSaturationChanged(floatVal);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (listener != null) {
            listener.onEditStarted();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (listener != null) {
            listener.onEditCompleted();
        }
    }

    public void resetControls() {
        seekbarSaturation.setProgress(10);
        seekbarBrightness.setProgress(100);
        seekbarContrast.setProgress(0);
    }

    public interface EditImageFragmentListener {
        void onBrightnessChanged(int brightness);

        void onContrastChanged(float f);

        void onSaturationChanged(float f);

        void onEditStarted();

        void onEditCompleted();
    }
}
