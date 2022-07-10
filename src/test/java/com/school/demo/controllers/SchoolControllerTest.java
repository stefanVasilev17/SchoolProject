package com.school.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.demo.converter.GenericConverter;
import com.school.demo.dto.SchoolDTO;
import com.school.demo.models.CreateSchoolModel;
import com.school.demo.services.SchoolService;
import com.school.demo.services.implementations.SchoolProgramImpl;
import com.school.demo.services.implementations.SchoolServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(SchoolController.class)
class SchoolControllerTest
{

  @MockBean
  private SchoolService     schoolService;
  @MockBean
  private ModelMapper       modelMapper;
  @MockBean
  private GenericConverter  genericConverter;
  @MockBean
  private SchoolServiceImpl schoolServiceImpl;
  @MockBean
  private SchoolProgramImpl schoolProgramImpl;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getSchool() throws Exception
  {
    SchoolDTO schoolDTO = new SchoolDTO();
    schoolDTO.setName("nbuJava");
    schoolDTO.setAddress("str.Boicho Ognqnov 17");
    when(schoolService.get(1)).thenReturn(schoolDTO);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/schools/{id}", 0)
        .content(objectMapper.writeValueAsString(schoolDTO))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void createSchool() throws Exception
  {
    CreateSchoolModel school = new CreateSchoolModel();
    school.setName("nbuJava");
    school.setAddress("str.Boicho Ognqnov 17");
    SchoolDTO schoolDTO = new SchoolDTO();
    schoolDTO.setName("nbuJava");
    schoolDTO.setAddress("str.Boicho Ognqnov 17");
    when(schoolService.create(any(CreateSchoolModel.class))).thenReturn(schoolDTO);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/schools/create")
        .content(objectMapper.writeValueAsString(school))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void editSchool() throws Exception
  {
    CreateSchoolModel school = new CreateSchoolModel();
    school.setName("nbuJava");
    school.setAddress("str.Boicho Ognqnov 17");
    SchoolDTO schoolDTO = new SchoolDTO();
    schoolDTO.setName("nbuJava");
    schoolDTO.setAddress("str.HOHOHO 124");
    when(schoolService.edit(any(Long.class), any(CreateSchoolModel.class))).thenReturn(Boolean.TRUE);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/schools/{id}/edit", 0)
        .content(objectMapper.writeValueAsString(school))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void deleteSchool() throws Exception
  {
    when(schoolService.delete(any(Long.class))).thenReturn(Boolean.TRUE);

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/schools/{id}/delete", 0)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void addDirector() throws Exception
  {
    when(schoolService.assignDirector(any(Long.class), any(Long.class))).thenReturn(Boolean.TRUE);

    mockMvc.perform(MockMvcRequestBuilders.put("/{id}/assign/director/{directorId}", 0, 0)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void removeDirector()
  {
  }

  @Test
  void addStudent()
  {
  }

  @Test
  void removeStudent()
  {
  }

  @Test
  void addTeacher()
  {
  }

  @Test
  void removeTeacher()
  {
  }

  @Test
  void addStudentToCourse()
  {
  }

  @Test
  void removeStudentFromCourse()
  {
  }

  @Test
  void avgGradeOnStudent()
  {
  }

  @Test
  void avgGradeOnStudentsWhoHaveMoreThenFourPointFive()
  {
  }

  @Test
  void avgGradeOnStudentsWhoHaveLessThenFourPointFive()
  {
  }

  @Test
  void createSchoolProgram()
  {
  }

  @Test
  void getSchoolProgram()
  {
  }
}
