package rafael.gabriel.caio.matheus.wheelieway.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ComentarioPagingSource extends ListenableFuturePagingSource<Integer, ComentarioItem> {

    WheelieWayRepository wheelieWayRepository;

    Integer initialLoadSize = 0;

    public ComentarioPagingSource(WheelieWayRepository wheelieWayRepository) {
        this.wheelieWayRepository = wheelieWayRepository;
    }

    /**
     * Toda vez que a lista precisa ser recarregada, esse método é chamado para decidir a partir de
     * qual ponto a lista será exibida. Retornar null siginifica que a lista sempre será recarregada
     * do início.
     */
    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, ComentarioItem> pagingState) {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.

        return null;
    }

    /**
     * Método que carrega uma página junto ao servidor web
     * @param
     * @return
     */


    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, ComentarioItem>> loadFuture(@NonNull PagingSource.LoadParams<Integer> loadParams) {

        // calcula os parâmetros de limit e offset que serão enviados ao servidor web
        Integer nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
            initialLoadSize = loadParams.getLoadSize();
        }

        Integer offSet = 0;
        if(nextPageNumber == 2) {
            offSet = initialLoadSize;
        }
        else {
            offSet = ((nextPageNumber - 1) * loadParams.getLoadSize()) + (initialLoadSize - loadParams.getLoadSize());
        }

        // cria uma nova linha de execução
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
        Integer finalOffSet = offSet;
        Integer finalNextPageNumber = nextPageNumber;

        // executa a nova linha de execução.
        ListenableFuture<LoadResult<Integer, ComentarioItem>> lf = service.submit(new Callable<LoadResult<Integer, ComentarioItem>>() {
            /**
             * Tudo que estiver dentro dessa função será executado na nova linha de execução.
             */


            @Override
            public PagingSource.LoadResult<Integer, ComentarioItem> call() {
                List<ComentarioItem> comentariosItemsList = null;
                // envia uma requisição para o servidor web pedindo por uma nova página de dados (bloco de produtos)
                comentariosItemsList = wheelieWayRepository.loadComentarios(loadParams.getLoadSize(), finalOffSet);
                Integer nextKey = null;
                if (comentariosItemsList.size() >= loadParams.getLoadSize()) {
                    nextKey = finalNextPageNumber + 1;
                }
                // monta uma página do padrão da biblioteca Paging 3.
                return new PagingSource.LoadResult.Page<Integer, ComentarioItem>(comentariosItemsList, null, nextKey);
            }
        });

        return lf;
    }
}