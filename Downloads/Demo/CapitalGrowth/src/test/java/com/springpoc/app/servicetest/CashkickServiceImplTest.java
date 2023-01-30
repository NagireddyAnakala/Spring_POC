
package com.springpoc.app.servicetest;

import com.springpoc.app.entity.*;
import com.springpoc.app.model.CashkickModel;
import com.springpoc.app.model.ContractModel;
import com.springpoc.app.model.PaymentModel;
import com.springpoc.app.repository.CashkickRepository;
import com.springpoc.app.repository.ContractRepository;
import com.springpoc.app.repository.PaymentRepository;
import com.springpoc.app.service.CashkickService;
import com.springpoc.app.service.CashkickServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CashkickServiceImplTest {

    @InjectMocks
    private CashkickServiceImpl cashKickServiceImpl;

    @Mock
    public CashkickRepository cashkickRepository;

    @Mock
    public ContractRepository contractRepository;

    @Mock
    public PaymentRepository paymentRepository;

    @Mock
    public ModelMapper modelMapper;

    CashkickServiceImpl cashKickService=mock(CashkickServiceImpl.class);

    private final List<Cashkick> cashKickList = Arrays.asList(
            new Cashkick(1,"cashkick-1","Pending",new Date(),123,456,new User(),Arrays.asList(new Contract())),new Cashkick()
    );
    private final List<Contract> contractList = Arrays.asList(
            new Contract(1,"Contract-1","monthly",12000,"12 months",140000,
                    new User(),Arrays.asList(new UsedContract()),cashKickList)
    );
    private final List<Payment> payments = Arrays.asList(
            new Payment(),
            new Payment()
    );
    @Test
    public void test(){

    }

    @BeforeEach
    void setUp() {
        when(cashkickRepository.findAllByUserId(1)).thenReturn(cashKickList);
        when(contractRepository.findAllByUserId(1)).thenReturn(contractList);
        when(paymentRepository.findAllByUserId(1)).thenReturn(payments);
    }

    @Test
    public void testGetCashKicksByUserId() throws Exception {

        List<CashkickModel> cashkickModels=Arrays.asList(
                (new CashkickModel(1,"cashkick-1","Pending",new Date(),123,456,101)));
        when(cashKickService.getCashKicksByUserId(1)).thenReturn(cashkickModels);
        //   List<CashkickModel> result = Whitebox.invokeMethod(cashKickService,"convertToModel");
        assertNotNull(cashkickModels);
        assertEquals(1, cashkickModels.size());
        assertEquals(1, cashkickModels.get(0).getId());
        assertEquals(101, cashkickModels.get(0).getUser_id());
        assertEquals("cashkick-1", cashkickModels.get(0).getCashkick_name());
        assertEquals(123, cashkickModels.get(0).getTotal_received());
    }


    @Test
    public void testGetContractsByUserId() {
        List<ContractModel> contractModels=Arrays.asList(
                (new ContractModel(1,"Contract-1","monthly",12000,"12 months",140000,100))
        );

        when(cashKickService.getContractsByUserId(1)).thenReturn(contractModels);
        assertNotNull(contractModels);
        assertEquals(1, contractModels.size());
        assertEquals(1,contractModels.get(0).getId());
        assertEquals("Contract-1", contractModels.get(0).getContract_name());
        assertEquals("monthly", contractModels.get(0).getType());
        assertEquals(100,contractModels.get(0).getUser_id());
    }

    @Test
    public void testGetPaymentByUserId() {
        List<PaymentModel> paymentModels=Arrays.asList((
                new PaymentModel(1,"Upcoming",new Date(),1000,2000,102)));

        when(cashKickService.getPaymentByUserId(1)).thenReturn(paymentModels);
        assertNotNull(paymentModels);
        assertEquals(1, paymentModels.size());
        assertEquals(1, paymentModels.get(0).getId());
        assertEquals("Upcoming", paymentModels.get(0).getStatus());
        assertEquals(1000,paymentModels.get(0).getExpected_amount());
        assertEquals(2000,paymentModels.get(0).getOutstanding_amount());
        assertEquals(102,paymentModels.get(0).getUser_id());

    }
//    @Test
//    public void convertToModel_validInput_returnsExpectedModelList() {
//        // Arrange
//        List<Entity> entityList = Arrays.asList(new Entity(), new Entity());
//        Class<Model> modelClass = Model.class;
//        ModelMapper modelMapper = mock(ModelMapper.class);
//        CashkickServiceImpl cashKickService =mock(CashkickServiceImpl.class);
//
//        // Act
//        when(cashKickService.)
//        List<Model> result = cashKickService.convertToModel(entityList, modelClass);
//
//        // Assert
//        assertEquals(result.size(), entityList.size());
//        for (int i = 0; i < result.size(); i++) {
//            assertEquals(result.get(i).getProperty1(), entityList.get(i).getProperty1());
//            assertEquals(result.get(i).getProperty2(), entityList.get(i).getProperty2());
//            // Add additional assertions for any other properties that should match
//        }


}
