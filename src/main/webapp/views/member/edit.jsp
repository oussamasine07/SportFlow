<%@ page import="com.sportflow.model.User, com.sportflow.model.Trainer, java.util.*" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    User user = (User) session.getAttribute("user");

    Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
    session.removeAttribute("errors");


    com.sportflow.dto.RegisterDTO old = (com.sportflow.dto.RegisterDTO) session.getAttribute("old");
    session.removeAttribute("old");

    String errorUser = (String) session.getAttribute("errorUser");
    session.removeAttribute("errorUser");
%>
    <jsp:include page="/views/parcials/sidebar.jsp" />
    <div class="flex flex-col flex-1 w-full">
        <jsp:include page="/views/parcials/navbar.jsp" />

        <main class="h-full overflow-y-auto">
            <div class="container px-6 mx-auto grid">
                <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
                  Edit Member <c:out value='${member.firstName}' />
                </h2>
                <div
                  class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
                >
                    <form action="${pageContext.request.contextPath}/member/edit?id=<c:out value='${member.id}' />" method="POST">
                        <div class="grid grid-cols-2">
                            <div class="grid-span-1 mt-4">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      First name
                                    </span>
                                    <input
                                        type="text"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Trainer first name"
                                      name="firstName"
                                      value="<c:out value='${member.firstName}' />"
                                    />
                                    <% if (errors != null && errors.containsKey("firstName")) { %>
                                          <span class="text-xs text-red-600 dark:text-red-400">
                                            <%= errors.get("firstName") %>
                                          </span>
                                      <% } %>
                                  </label>
                            </div>

                            <div class="grid-span-1 mt-4">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      Last name
                                    </span>
                                    <input
                                        type="text"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Trainer last name"
                                      name="lastName"
                                      value="<c:out value='${member.lastName}' />"
                                    />
                                    <% if (errors != null && errors.containsKey("lastName")) { %>
                                        <span class="text-xs text-red-600 dark:text-red-400">
                                          <%= errors.get("lastName") %>
                                        </span>
                                    <% } %>
                                  </label>
                            </div>
                            <div class="grid-span-1 mt-4">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      Email
                                    </span>
                                    <input
                                    type="text"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Trainer email"
                                      name="email"
                                      value="<c:out value='${member.email}' />"
                                    />
                                    <% if (errors != null && errors.containsKey("email")) { %>
                                          <span class="text-xs text-red-600 dark:text-red-400">
                                            <%= errors.get("email") %>
                                          </span>
                                    <% } %>
                                  </label>
                            </div>
                            <div class="grid-span-1">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      Date of birth
                                    </span>
                                    <input
                                        type="date"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Member email"
                                        name="dateOfBirth"
                                        value="<c:out value='${member.dateOfBirth}' />"
                                    />
                                    <% if (errors != null && errors.containsKey("dateOfBirth")) { %>
                                          <span class="text-xs text-red-600 dark:text-red-400">
                                            <%= errors.get("dateOfBirth") %>
                                          </span>
                                    <% } %>
                                  </label>
                            </div>

                            <button
                                type="submit"
                                class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                                >
                                Update trainer
                            </button>

                        </div>
                    </form>
                </div>
            </div>
        </main>

