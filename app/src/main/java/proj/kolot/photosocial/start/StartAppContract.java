package proj.kolot.photosocial.start;


public interface StartAppContract {
    interface View{
        void setPresenter(StartAppContract.Presenter presenter);
        void showPhotoNews();
        void hidePhotoNews();
        void showUnautorization();
        void showLogin();

    }
    interface Presenter{
        boolean isLogin();
        void login();
    }
}
