package com.hundsun.winner.skin_module.callback;

/**
 * Created by william_zhang on 2017/9/18.
 */
public interface ISkinChangingCallback {
    void onStart();

    void onError(Exception e);

    void onComplete();

    public static DefaultSkinChangingCallback DEFAULT_SKIN_CHANGING_CALLBACK = new DefaultSkinChangingCallback();

    public class DefaultSkinChangingCallback implements ISkinChangingCallback {
        @Override
        public void onStart() {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
