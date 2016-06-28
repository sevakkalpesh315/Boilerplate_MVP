package math.sevakkalpesh.com.boilerplate_mvp.model.observables;

import java.util.List;

import math.sevakkalpesh.com.boilerplate_mvp.model.Cake_model;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by kalpesh on 22/12/2015.
 */
public interface Cake_API {
    @GET("198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    Observable<List<Cake_model>> getCakes();


}
