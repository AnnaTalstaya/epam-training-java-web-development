package by.talstaya.crackertracker.command;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for realization pagination
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public interface Pagination {

    /**
     * This method initialise parameters for pagination
     * @param request the request that came
     * @param numberOfObjectsPerPage default number of objects displayed on the page
     * @param objectsPerPageParam number of objects displayed on the page
     * @param indexOfPageParam current index of displayed page
     * @param startIndexOfObjectListParam start index of array for display objects
     * @param changePage if this value is -1, then the page index decreases by 1, but if this value is 1, then the page index increases by 1
     *
     */
    default void initPaginationParams(HttpServletRequest request,
                                      int numberOfObjectsPerPage,
                                      String objectsPerPageParam,
                                      String indexOfPageParam,
                                      String startIndexOfObjectListParam,
                                      String changePage) {
        int objectsPerPage;
        if (request.getParameter(objectsPerPageParam) == null) {  //if we open the jsp the first time
            objectsPerPage = numberOfObjectsPerPage;
        } else {
            objectsPerPage = Integer.parseInt(request.getParameter(objectsPerPageParam));
        }

        int newIndex;
        if (request.getParameter(indexOfPageParam) == null) {
            newIndex = 1;
        } else {
            newIndex = (int) Double.parseDouble(request.getParameter(indexOfPageParam))
                    + Integer.parseInt(request.getParameter(changePage));
        }

        request.setAttribute(objectsPerPageParam, objectsPerPage);
        request.setAttribute(startIndexOfObjectListParam, (newIndex - 1) * objectsPerPage);
        request.setAttribute(indexOfPageParam, newIndex);
    }
}
