<%@ page import="com.sportflow.model.User, com.sportflow.model.Trainer, java.util.*" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%
    User user = (User) session.getAttribute("user");
%>
    <jsp:include page="/views/parcials/sidebar.jsp" />
    <div class="flex flex-col flex-1 w-full">
        <jsp:include page="/views/parcials/navbar.jsp" />

        <main class="h-full overflow-y-auto">
            <div class="container px-6 mx-auto grid">
                <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
                  list all trainers
                </h2>

                <div class="w-full overflow-hidden rounded-lg shadow-xs">
                  <div class="w-full overflow-x-auto">

                    <c:choose>
                      <c:when test="${fn:length(trainers) > 0}">
                          <table class="w-full whitespace-no-wrap">
                            <thead>
                              <tr
                                class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                              >
                                <th class="px-4 py-3">Trainer</th>
                                <th class="px-4 py-3">Specialty</th>
                                <th class="px-4 py-3">Actions</th>
                              </tr>
                            </thead>
                            <tbody
                              class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                            >
                              <c:forEach var="trainer" items="${trainers}">
                                <tr class="text-gray-700 dark:text-gray-400">
                                    <td class="px-4 py-3">
                                      <div class="flex items-center text-sm">
                                        <!-- Avatar with inset shadow -->
                                        <div
                                          class="relative hidden w-8 h-8 mr-3 rounded-full md:block"
                                        >
                                          <img
                                            class="object-cover w-full h-full rounded-full"
                                            src="https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjE3Nzg0fQ"
                                            alt=""
                                            loading="lazy"
                                          />
                                          <div
                                            class="absolute inset-0 rounded-full shadow-inner"
                                            aria-hidden="true"
                                          ></div>
                                        </div>
                                        <div>
                                          <p class="font-semibold">
                                            <c:out value="${trainer.firstName}" /> <c:out value="${trainer.lastName}" />
                                          </p>
                                          <p class="text-xs text-gray-600 dark:text-gray-400">
                                             <c:out value="${trainer.email}" />
                                          </p>
                                        </div>
                                      </div>
                                    </td>
                                    <td class="px-4 py-3">
                                      <div class="flex items-center text-sm">
                                        <!-- Avatar with inset shadow -->
                                        <div>
                                          <p class="font-semibold">
                                            <c:out value="${trainer.specialty}" />
                                          </p>
                                        </div>
                                      </div>
                                    </td>
                                    <td class="px-4 py-3">
                                      <div class="flex items-center space-x-4 text-sm">
                                        <a
                                          class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray"
                                          aria-label="Edit"
                                          href="${pageContext.request.contextPath}/trainer/edit?id=<c:out value='${trainer.id}' />"
                                        >
                                          <svg
                                            class="w-5 h-5"
                                            aria-hidden="true"
                                            fill="currentColor"
                                            viewBox="0 0 20 20"
                                          >
                                            <path
                                              d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z"
                                            ></path>
                                          </svg>
                                        </a>
                                        <form action="${pageContext.request.contextPath}/trainer/delete?id=<c:out value='${trainer.id}' />" method="POST">
                                            <button
                                              class="flex items-center justify-between px-2 py-2 text-sm font-medium leading-5 text-purple-600 rounded-lg dark:text-gray-400 focus:outline-none focus:shadow-outline-gray"
                                              aria-label="Delete"
                                              type="submit"
                                            >
                                              <svg
                                                class="w-5 h-5"
                                                aria-hidden="true"
                                                fill="currentColor"
                                                viewBox="0 0 20 20"
                                              >
                                                <path
                                                  fill-rule="evenodd"
                                                  d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                                                  clip-rule="evenodd"
                                                ></path>
                                              </svg>
                                            </button>
                                        </form>

                                      </div>
                                    </td>
                                  </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                      </c:when>
                      <c:otherwise>
                          <h2 class="my-6 text-xl font-semibold text-gray-700 dark:text-gray-200">
                            No trianers listed
                          </h2>
                      </c:otherwise>
                    </c:choose>


                  </div>

                </div>
            </div>
        </main>

    </div>





