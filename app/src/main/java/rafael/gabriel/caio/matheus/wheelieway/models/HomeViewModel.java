package rafael.gabriel.caio.matheus.wheelieway.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

  LiveData<PagingData<EstabelecimentoItem>> estabelecimentosLd;

  public HomeViewModel(@NonNull Application application) {
      super(application);

      WheelieWayRepository wheelieWayRepository = new WheelieWayRepository(getApplication());
  }
}
