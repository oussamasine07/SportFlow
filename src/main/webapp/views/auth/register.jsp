<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>

<%

    Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
    session.removeAttribute("errors");


    com.sportflow.dto.RegisterDTO old = (com.sportflow.dto.RegisterDTO) session.getAttribute("old");
    session.removeAttribute("old");

    String errorUser = (String) session.getAttribute("errorUser");
    session.removeAttribute("errorUser");
%>

<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create account - Windmill Dashboard</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="${pageContext.request.contextPath}/resources/assets/js/init-alpine.js"></script>
  </head>
  <body>
    <div class="flex items-center min-h-screen p-6 bg-gray-50 dark:bg-gray-900">
      <div
        class="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl dark:bg-gray-800"
      >
        <div class="flex flex-col overflow-y-auto md:flex-row">
          <div class="h-32 md:h-auto md:w-1/2">
            <img
              aria-hidden="true"
              class="object-cover w-full h-full dark:hidden"
              src="${pageContext.request.contextPath}/resources/assets/img/register.jpg"
              alt="Office"
            />
            <img
              aria-hidden="true"
              class="hidden object-cover w-full h-full dark:block"
              src="${pageContext.request.contextPath}/resources/assets/img/register.jpg"
              alt="Office"
            />
          </div>
          <div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
            <div class="w-full">
              <h1
                class="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200"
              >
                Create account
              </h1>


              <form action="${pageContext.request.contextPath}/auth/register" method="POST">
                <label class="block text-sm">
                    <span class="text-gray-700 dark:text-gray-400">First name</span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      placeholder="Jane Doe"
                      name="firstName"
                      value="<%= old != null ? old.getFirstName() : "" %>"
                    />
                      <% if (errors != null && errors.containsKey("firstName")) { %>
                          <span class="text-xs text-red-600 dark:text-red-400">
                            <%= errors.get("firstName") %>
                          </span>
                      <% } %>
                  </label>
                  <label class="block text-sm">
                      <span class="text-gray-700 dark:text-gray-400">Last name</span>
                      <input
                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                        placeholder="Jane Doe"
                        name="lastName"
                        value="<%= old != null ? old.getLastName() : "" %>"
                      />
                      <% if (errors != null && errors.containsKey("lastName")) { %>
                            <span class="text-xs text-red-600 dark:text-red-400">
                              <%= errors.get("lastName") %>
                            </span>
                        <% } %>
                  </label>
                  <label class="block text-sm">
                        <span class="text-gray-700 dark:text-gray-400">Email</span>
                        <input
                          class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                          placeholder="Jane Doe"
                          name="email"
                          value="<%= old != null ? old.getEmail() : "" %>"
                        />
                        <% if (errors != null && errors.containsKey("email")) { %>
                              <span class="text-xs text-red-600 dark:text-red-400">
                                <%= errors.get("email") %>
                              </span>
                        <% } %>
                  </label>
                  <label class="block mt-4 text-sm">
                    <span class="text-gray-700 dark:text-gray-400">Password</span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      placeholder="***************"
                      type="password"
                      name="password"
                    />
                    <% if (errors != null && errors.containsKey("password")) { %>
                          <span class="text-xs text-red-600 dark:text-red-400">
                            <%= errors.get("password") %>
                          </span>
                    <% } %>
                  </label>
                  <label class="block mt-4 text-sm">
                    <span class="text-gray-700 dark:text-gray-400">
                      Confirm password
                    </span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      placeholder="***************"
                      type="password"
                      name="confirmPassword"
                    />
                    <% if (errors != null && errors.containsKey("confirmPassword")) { %>
                          <span class="text-xs text-red-600 dark:text-red-400">
                            <%= errors.get("confirmPassword") %>
                          </span>
                    <% } %>
                  </label>

                  <!-- You should use a button here, as the anchor is only used for the example  -->
                  <button
                    type="submit"
                    class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                    >
                    Create account
                  </button>
              </form>

              <p class="mt-4">
                <a
                  class="text-sm font-medium text-purple-600 dark:text-purple-400 hover:underline"
                  href="${pageContext.request.contextPath}/auth/login"
                >
                  Already have an account? Login
                </a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
