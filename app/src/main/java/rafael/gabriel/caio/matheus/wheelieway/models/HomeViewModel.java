package rafael.gabriel.caio.matheus.wheelieway.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import kotlinx.coroutines.CoroutineScope;
import rafael.gabriel.caio.matheus.wheelieway.R;

public class HomeViewModel extends AndroidViewModel {

    public int getNavigationOpSelected() {
        return navigationOpSelected;
    }

    public void setNavigationOpSelected(int navigationOpSelected) {
        this.navigationOpSelected = navigationOpSelected;
    }

    int navigationOpSelected = R.id.optHome;

    LiveData<PagingData<EstabelecimentoItem>> estabelecimentosLd;

  public HomeViewModel(@NonNull Application application) {
      super(application);

      WheelieWayRepository wheelieWayRepository = new WheelieWayRepository(getApplication());
      CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
      Pager<Integer, EstabelecimentoItem> pager = new Pager(new PagingConfig(10), () -> new EstabelecimentoPagingSource(wheelieWayRepository));
      estabelecimentosLd = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
  }

    public LiveData<PagingData<EstabelecimentoItem>> getEstabelecimentosLd() {return estabelecimentosLd;}
}
