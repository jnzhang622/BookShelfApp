package com.example.bookshelfapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookshelfapp.R;
import com.example.bookshelfapp.databinding.FormFragmentBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FormFragment extends Fragment {
    public static final String TAG = FormFragment.class.getName();

    private BookViewModel mBookViewModel;

    private FormFragmentBinding binding;
    private CompositeDisposable disposable = new CompositeDisposable();

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FormFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBookViewModel = new ViewModelProvider(requireActivity(),
                new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()))
                .get(BookViewModel.class);


        binding.saveButton.setOnClickListener(view1 -> {
                    disposable.add(mBookViewModel.createBook(
                            binding.titleEt.getText().toString(),
                            binding.authorEt.getText().toString(),
                            binding.genreEt.getText().toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError(this::onAddFailure)
                            .subscribe(this::onAddSuccess));

                    binding.titleEt.setText("");
                    binding.authorEt.setText("");
                    binding.genreEt.setText("");
                }
        );

        binding.showBooksButton.setOnClickListener(view12 ->
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BooksFragment()).addToBackStack(null).commit());

    }

    private void onAddSuccess() {
        Toast.makeText(requireContext(), "Book has been added", Toast.LENGTH_LONG).show();
    }

    private void onAddFailure(Throwable throwable) {
        Log.d(TAG, throwable.toString());
        Toast.makeText(requireContext(), "Book failed to be added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        disposable.dispose();
        super.onDestroyView();
    }
}