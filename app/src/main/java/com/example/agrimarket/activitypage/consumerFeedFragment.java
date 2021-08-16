package com.example.agrimarket.activitypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.FragmentConsumerFeedBinding;

import java.util.List;

import Adapters.feedListAdapter;
import Controller.postController;
import View.FeedView;
import View.ResultView;
import model.Posts;
import model.Result;

public class consumerFeedFragment extends Fragment implements ResultView, FeedView {
    FragmentConsumerFeedBinding binding;
    postController postController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConsumerFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        postController = new postController(this, this, getContext());
        postController.getPost();
        return view;
    }

    @Override
    public void feedReady(List<Posts> feeds) {

        binding.rvConsumerFeed.setLayoutManager(new LinearLayoutManager(getContext()));
        feedListAdapter feedListAdapter = new feedListAdapter(feeds);
        binding.rvConsumerFeed.setAdapter(feedListAdapter);
    }

    @Override
    public void responseReady(Result result) {

    }
}