package com.example.experiment2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.experiment2.view.ClockView;
import com.example.experiment2.view.GameView;

public class GameViewFragment extends Fragment {
    private GameView gameView; // 添加这个变量
    public GameViewFragment() {
        // Required empty public constructor
    }

    public static GameViewFragment newInstance() {
        GameViewFragment fragment = new GameViewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_view, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gameView = view.findViewById(R.id.clockView); // 获取 ClockView 实例
//        gameView.start(); // 启动时钟
    }
}