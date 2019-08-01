package by.talstaya.crackertracker.command;

import javax.servlet.http.HttpServletRequest;

public interface Pagination {

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
