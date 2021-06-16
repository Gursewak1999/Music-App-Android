package production.jattbrand.musicapp.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import production.jattbrand.musicapp.R;
import production.jattbrand.musicapp.misc.Actions;
import production.jattbrand.musicapp.misc.MusicService;
import production.jattbrand.musicapp.modals.BasicSongDetail;
import production.jattbrand.musicapp.modals.HomeDetails;
import production.jattbrand.musicapp.modals.SongDetail;
import production.jattbrand.musicapp.network.NetworkUtils;
import production.jattbrand.musicapp.ui.main.HomeHorizontalAdapter;
import production.jattbrand.musicapp.ui.main.base.BaseMainFragment;
import production.jattbrand.musicapp.utils.helpers.PreferenceManager;

public class MainFragment extends BaseMainFragment {

    private String TAG = "MainFragment";

    @BindView(R.id.editors_recycler)
    public RecyclerView editors_recycler;
    @BindView(R.id.new_release_recycler)
    public RecyclerView new_release_recycler;
    @BindView(R.id.punjabi_albums_recycler)
    public RecyclerView punjabi_albums_recycler;
    @BindView(R.id.hindi_albums_recycler)
    public RecyclerView hindi_albums_recycler;
    @BindView(R.id.hindi_single_recycler)
    public RecyclerView hindi_single_recycler;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        initBase();
        parentActivity.setTitle("Discover New");
        parentActivity.setFullBounds();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {

                showProgressBar();
                init();
                Observable<HomeDetails> disposable = NetworkUtils.getClient().getHomeDetails();

                disposable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete(() -> {
                            //refresh();
                        })
                        .subscribeWith(new Observer<HomeDetails>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(HomeDetails s) {
                                new PreferenceManager(requireContext()).putString(PreferenceManager.HOME_DATA, new Gson().toJson(s));
                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.e("String", "error " + e.getLocalizedMessage() + " " + e.toString());
                            }

                            @Override
                            public void onComplete() {
                                refresh();
                                hideProgressBar();
                            }
                        });

            }
        }).start();
    }

    private void init() {
        if (editors_recycler.getLayoutManager() == null) {
            editors_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            new_release_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            punjabi_albums_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            hindi_albums_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            hindi_single_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    private void refresh() {

        HomeDetails data = new Gson().fromJson(new PreferenceManager(getContext()).getStringValue(PreferenceManager.HOME_DATA), HomeDetails.class);
        editors_recycler.setAdapter(new HomeHorizontalAdapter(getContext(), data.getEditorsChoice(), new HomeHorizontalAdapter.Listener() {
            @Override
            public void onCLick(View v, int position, BasicSongDetail songDetail) {
                playSong(songDetail);
            }
        }));
        new_release_recycler.setAdapter(new HomeHorizontalAdapter(getContext(), data.getNewRelease(), new HomeHorizontalAdapter.Listener() {
            @Override
            public void onCLick(View v, int position, BasicSongDetail songDetail) {
                playSong(songDetail);
            }
        }));
        punjabi_albums_recycler.setAdapter(new HomeHorizontalAdapter(getContext(), data.getPunjabiAlbums(), null));
        hindi_albums_recycler.setAdapter(new HomeHorizontalAdapter(getContext(), data.getHindiAlbums(), null));
        hindi_single_recycler.setAdapter(new HomeHorizontalAdapter(getContext(), data.getHindiSingleTracks(), null));
    }

    private void playSong(BasicSongDetail songDetail) {
        showProgressBar();
        NetworkUtils.getClient().getSongDetails(songDetail.getName(), songDetail.getCover(), songDetail.getUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    //refresh();
                })
                .subscribeWith(new Observer<SongDetail>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull SongDetail songDetail) {
                        Log.e(TAG, "onNext: " + songDetail.toString());
                        Intent svc = new Intent(parentActivity, MusicService.class);
                        svc.setAction(Actions.ACTION_PLAY_NEW);
                        svc.putExtra("url", songDetail.getDownloadLinks().get(0).get48());
                        parentActivity.setPlayerSong(songDetail);
                        parentActivity.startService(svc);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                        hideProgressBar();
                    }
                });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initBase();
    }


}