package com.ramnivas.scrollbehavior;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ScrollingActivity extends AppCompatActivity {

    private RecyclerView recylerView;
    private ScrollingAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private int firstVisibleItem = 0;
    private boolean avoidFirstUiCreationScroll = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        recylerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ScrollingAdapter();
        recylerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recylerView.setLayoutManager(linearLayoutManager);
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        recylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final View header = recyclerView.getChildAt(0).findViewById(R.id.header);
                int currentFirstVisible = linearLayoutManager.findFirstVisibleItemPosition();
                if (!avoidFirstUiCreationScroll) {
                    firstVisibleItem = currentFirstVisible;
                    avoidFirstUiCreationScroll = true;
                    return;
                }
                if(header != null && (dy > 0 ||  currentFirstVisible > firstVisibleItem && header.getVisibility() == View.VISIBLE)) {
                    header.animate().alpha(0f).setDuration(300)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (recyclerView.getLayoutManager().findViewByPosition(0) != null) {
                                        recyclerView.getLayoutManager().findViewByPosition(0).setVisibility(View.GONE);
                                    }
                                    if (header != null) {
                                        header.setVisibility(View.GONE);
                                    }
                                    recyclerView.scrollToPosition(1);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                } else if (header != null && (dy < 0) || (currentFirstVisible < firstVisibleItem && header.getVisibility() == View.GONE)){
                    header.animate().alpha(1f).setDuration(300)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (recyclerView.getLayoutManager().findViewByPosition(0) != null) {
                                        recyclerView.getLayoutManager().findViewByPosition(0).setVisibility(View.VISIBLE);
                                    }
                                    if (header != null) {
                                        header.setVisibility(View.VISIBLE);
                                    }
                                    recyclerView.scrollToPosition(0);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                }
                firstVisibleItem = currentFirstVisible;
            }
        });
    }

}
