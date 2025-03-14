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
                  Add Member
                </h2>
                <div
                  class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
                >
                    <form action="${pageContext.request.contextPath}/member/create" method="POST">
                        <div class="grid grid-cols-2">
                            <div class="grid-span-1">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      First name
                                    </span>
                                    <input
                                        type="text"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Member first name"
                                      name="firstName"
                                      value="<%= old != null ? old.getFirstName() : "" %>"
                                    />
                                    <% if (errors != null && errors.containsKey("firstName")) { %>
                                          <span class="text-xs text-red-600 dark:text-red-400">
                                            <%= errors.get("firstName") %>
                                          </span>
                                      <% } %>
                                  </label>
                            </div>

                            <div class="grid-span-1">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      Last name
                                    </span>
                                    <input
                                        type="text"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Member last name"
                                      name="lastName"
                                      value="<%= old != null ? old.getLastName() : "" %>"
                                    />
                                    <% if (errors != null && errors.containsKey("lastName")) { %>
                                        <span class="text-xs text-red-600 dark:text-red-400">
                                          <%= errors.get("lastName") %>
                                        </span>
                                    <% } %>
                                  </label>
                            </div>
                            <div class="grid-span-1">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      Email
                                    </span>
                                    <input
                                    type="text"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Member email"
                                      name="email"
                                      value="<%= old != null ? old.getEmail() : "" %>"
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
                                      Password
                                    </span>
                                    <input
                                        type="password"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Member password"
                                      name="password"
                                    />
                                    <% if (errors != null && errors.containsKey("password")) { %>
                                          <span class="text-xs text-red-600 dark:text-red-400">
                                            <%= errors.get("password") %>
                                          </span>
                                    <% } %>
                                  </label>
                            </div>
                            <div class="grid-span-1">
                                  <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">
                                      Confirm Password
                                    </span>
                                    <input
                                        type="password"
                                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                      placeholder="Member password"
                                      name="confirmPassword"
                                    />
                                    <% if (errors != null && errors.containsKey("confirmPassword")) { %>
                                          <span class="text-xs text-red-600 dark:text-red-400">
                                            <%= errors.get("confirmPassword") %>
                                          </span>
                                    <% } %>
                                  </label>
                            </div>
                            <button
                                type="submit"
                                class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                                >
                                Create trainer
                            </button>

                        </div>
                    </form>




                </div>
            </div>
        </main>

