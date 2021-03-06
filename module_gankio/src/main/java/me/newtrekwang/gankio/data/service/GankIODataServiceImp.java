package me.newtrekwang.gankio.data.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import me.newtrekwang.gankio.data.protocal.GankIOBaseResp;
import me.newtrekwang.gankio.data.protocal.NewsItem;
import me.newtrekwang.gankio.data.remote.GankIORepository;
import me.newtrekwang.gankio.utils.BaseFunc;
import me.newtrekwang.base.utils.ExceptionHandle;

/**
 * @className GankIODataServiceImp
 * @createDate 2019/5/11 17:45
 * @author newtrekWang
 * @email 408030208@qq.com
 * @desc Gankio 数据服务实现类，包含远程和本地数据源
 *
 */
public class GankIODataServiceImp implements GankIODataService {

    @Inject
    public GankIODataServiceImp(){}

    @Inject
    GankIORepository gankIORepository;

    @Override
    public Observable<List<String>> getHistoryDateList() {
        return gankIORepository.getHistoryDateList().flatMap(new BaseFunc<>());
    }

    @Override
    public Observable<GankIOBaseResp<Map<String,List<NewsItem>>>> getDailyData(int year, int month, int day) {
        return gankIORepository.getDailyData(year, month, day).flatMap(new Function<GankIOBaseResp<Map<String,List<NewsItem>>>, Observable<GankIOBaseResp<Map<String,List<NewsItem>>>>>() {
            @Override
            public Observable<GankIOBaseResp<Map<String,List<NewsItem>>>> apply(GankIOBaseResp<Map<String,List<NewsItem>>> stringGankIOBaseResp) throws Exception {
                return stringGankIOBaseResp.isError()?Observable.error(new ExceptionHandle.ResponseException(new Throwable("error"), ExceptionHandle.ERROR.CUSTOM_ERROR)):Observable.just(stringGankIOBaseResp);
            }
        });
    }

    @Override
    public Observable<List<NewsItem>> getNewsData(String title, int page) {
        return gankIORepository.getNewsItemList(title, page).flatMap(new BaseFunc<>());
    }

    @Override
    public Observable<List<NewsItem>> getMeizhi(int page) {
        return gankIORepository.getMeizhiList(page).flatMap(new BaseFunc<>());
    }
}
