//package org.example;
//
//import org.example.entity.Department;
//import org.example.repository.DepartmentRepository;
//import org.example.service.impl.DepartmentServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class UnitDepartmentTest {
//    @Mock
//    private DepartmentRepository departmentRepositoryMock;
//
//    @InjectMocks
//    private DepartmentServiceImpl departmentServiceMock;
//
//    @Test
//    public void testSaveDepartment() {
//        Department department = new Department(1,"Java Department");
//        when(departmentRepositoryMock.save(department)).thenReturn(department);
//        Department savedDepartment = departmentServiceMock.save(department);
//        assertEquals("Java Department", savedDepartment.getTitle());
//        assertThat(savedDepartment.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void shouldReturnAllDepartments() {
//        List<Department> departments = new ArrayList<>();
//        departments.add(new Department());
//        when(departmentRepositoryMock.findAll()).thenReturn(departments);
//        List<Department> departments1 = departmentServiceMock.findAll();
//        assertThat(departments.equals(departments1));
//    }
//
//    @Test
//    public void shouldReturnDepartmentById() {
//        Integer id = 1;
//        Department department = new Department();
//        department.setId(id);
//        when(departmentRepositoryMock.findById(id)).thenReturn(Optional.of(department));
//        assertThat(department.equals(departmentRepositoryMock.findById(id)));
//    }
//
//    @Test
//    public void get_should_throw_exception_when_id_doesnt_exist() {
//        assertThrows(NoSuchElementException.class, () -> departmentServiceMock.findById(50));
//    }
//
//
//    @Test
//    public void whenSaveDepartment_shouldReturnDepartment() {
//        Department department = new Department();
//        department.setTitle("TestName");
//        when(departmentRepositoryMock.save(ArgumentMatchers.any(Department.class))).thenReturn(department);
//        Department created = departmentServiceMock.save(department);
//        assertThat(created.getTitle()).isSameAs(department.getTitle());
//        verify(departmentRepositoryMock).save(department);
//    }
//
//    @Test
//    public void whenGivenId_shouldUpdateDepartment_ifFound() {
//        Department department = new Department();
//        department.setId(89);
//        department.setTitle("Test Name");
//
//        Department newDepartment = new Department();
//        newDepartment.setTitle("Java Department");
//
//        given(departmentRepositoryMock.findById(89)).willReturn(Optional.of(department));
//        departmentServiceMock.update(89, newDepartment);
//
//        verify(departmentRepositoryMock).save(department);
//        assertEquals("Java Department", department.getTitle());
//    }
//
//    @Test
//    public void should_throw_exception_when_update_department_doesnt_exist() {
//        assertThrows(NoSuchElementException.class, () -> departmentServiceMock.update(50, new Department()));
//    }
//
//    @Test
//    public void whenGivenId_shouldDeleteDepartment_ifFound() {
//        Department department = new Department();
//        department.setTitle("TestName");
//        department.setId(1);
//        lenient().when(departmentRepositoryMock.findById(department.getId())).thenReturn(java.util.Optional.of(department));
//        departmentServiceMock.deleteById(department.getId());
//        verify(departmentRepositoryMock).deleteById(department.getId());
//    }
//
//    @Test
//    public void should_throw_exception_when_department_doesnt_exist() {
//        assertThrows(NoSuchElementException.class, () -> departmentServiceMock.deleteById(50));
//    }
//}
