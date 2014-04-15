import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaginationHelper implements Helper<Object> {

	private static final Logger logger = LoggerFactory.getLogger(PaginationHelper.class);

	public static final String HELPER_NAME = "pagination";

	@Override
	public CharSequence apply(Object context, Options options) throws IOException {

		Map<String, Object> paginationInfoMap;

		try {
			int currentPage = options.param(0, 1); // 파라미터 - 현재 페이지 번호
			int totalPageCount = options.param(1, 1); // 파라미터 - 전체 페이지 개수
			int countPerPageGroup = options.param(2, 10); // 파라미터 - 페이지 그룹 노출 개수

			int firstPageIdx = (((currentPage - 1) / countPerPageGroup)) * countPerPageGroup + 1; // 노출되는 첫번째 index
			int lastPageIdx = (((currentPage - 1) / countPerPageGroup)) * countPerPageGroup + countPerPageGroup; // 노출되는 마지막 index

			int previousIdx = lastPageIdx - countPerPageGroup; // 이전 currentPage index
			int nextIdx = lastPageIdx + 1; // 다음 currentPage index

			boolean canGoPrevious = firstPageIdx > 1 ? true : false; // previous 버튼 active 여부
			boolean canGoNext = totalPageCount > lastPageIdx ? true : false; // next 버튼 active 여부

			int displayedLastPage = totalPageCount < lastPageIdx ? totalPageCount : lastPageIdx;

			paginationInfoMap = this.makePaginationMap(canGoPrevious, canGoNext, currentPage, firstPageIdx, displayedLastPage, previousIdx, nextIdx);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			paginationInfoMap = Maps.newHashMap();
		}

		return options.fn(paginationInfoMap);
	}

	private Map<String, Object> makePaginationMap(boolean canGoPrevious, boolean canGoNext, int page, int firstPage, int displayedLastPage,
		int previousIdx, int nextIdx) {

		Map<String, Object> paginationInfoMap = Maps.newHashMap();

		List<Map> pageList = Lists.newArrayList();

		for (int i = firstPage; i <= displayedLastPage; i++) {
			Map<String, Object> numberMap = Maps.newHashMap();
			numberMap.put("page", String.valueOf(i));
			numberMap.put("isCurrent", (i == page ? true : false));
			pageList.add(numberMap);
		}

		paginationInfoMap.put("canGoPrevious", canGoPrevious);
		paginationInfoMap.put("previousIdx", previousIdx);
		paginationInfoMap.put("pages", pageList);
		paginationInfoMap.put("canGoNext", canGoNext);
		paginationInfoMap.put("nextIdx", nextIdx);

		return paginationInfoMap;
	}
}
