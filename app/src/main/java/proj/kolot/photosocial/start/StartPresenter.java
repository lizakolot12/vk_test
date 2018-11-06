package proj.kolot.photosocial.start;

/**
 * Created by yarik on 1/19/17.
 */

public class StartPresenter implements StartAppContract.Presenter {

    private final StartAppContract.View mStartView;



    public StartPresenter(StartAppContract.View tasksView) {

        mStartView = tasksView;

        mStartView.setPresenter(this);
    }

    @Override
    public void login() {
     //   https://oauth.vk.com/authorize?client_id=5836524&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.52

       // groups photos
    }

    @Override
    public boolean isLogin() {
        return false;
    }
}
