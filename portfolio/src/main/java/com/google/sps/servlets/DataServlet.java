// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.gson.Gson;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private ArrayList phrases;

  /**
   * Composes an arraylist with quotes from some black
   * Authors that I have read.
   */
  @Override
  public void init() {
    phrases = new ArrayList<String>();
    // phrases.add(
    //     "In the whole world no poor devil is lynched, "
    //     + "no wretch is tortured, in whom I too am not "
    //     + "degraded and murdered. -Aime Cesaire");
    // phrases.add(
    //     "Sometimes we are blessed with being able to choose "
    //     + "the time, and the arena, and the manner of our "
    //     + "revolution, but more usually we must do battle "
    //     + "where we are standing. -Audre Lorde");
    // phrases.add(
    //     "He who is reluctant to recognize me opposes me. "
    //     + "-Frantz Fanon" );
  }  

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = convertToJson(phrases);
    
    response.setContentType("application/json;");
    response.getWriter().println(json);

  }

    /**
   * Converts a ServerStats instance into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  private String convertToJson(ArrayList<String> sample) {
    Gson gson = new Gson();
    String json = gson.toJson(sample);
    return json;
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Retrieve user's input from the form
    String userString = getParameter(request, "text-input", "");

    phrases.add(userString);
    response.setContentType("text/html");
    response.getWriter().println(userString);

    
    // Redirect back to the HTML page.
    response.sendRedirect("/forum.html");
  }
  
    /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
