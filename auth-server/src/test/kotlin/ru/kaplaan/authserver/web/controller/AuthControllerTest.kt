//package ru.kaplaan.authserver.web.controller
//
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.junit.jupiter.MockitoExtension
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import ru.kaplaan.authserver.service.AuthService
//
//@ExtendWith(MockitoExtension::class)
//class AuthControllerTest(
//    @Mock
//    private val authService: AuthService,
//
//    @InjectMocks
//    private val authController: AuthController
//) {
//
//    lateinit var mockMvc: MockMvc
//
//
//    @BeforeEach
//    fun setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(authController).build()
//    }
//
//
//    @Test
//    fun registration(){
//        mockMvc.perform(MockMvcRequestBuilders.get("/auth/registration/user"))
//            .andExpect(MockMvcResultMatchers.)
//    }
//
//
//
//}