package com.diycomputerscience.refactored;

import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;
import static org.mockito.Mockito.*;

public final class HelloCommandTest {
    @Test
    public void helloCommandShouldAcceptZeroParameters() {
        Assert.assertEquals(0, Commands.HELLO.getNumberOfArguments());
    }

    @Test
    public void verifyCommandName() {
        Assert.assertEquals("hello", Commands.HELLO.getCommandName());
    }

    @Test
    public void shouldRespondWithLoginFirstWhenNotLoggedIn() throws Exception {
        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.isLoggedIn()).thenReturn(false);
        when(context.getResponder()).thenReturn(responder);

        boolean sessionIsValid = Commands.HELLO.execute(context, Collections.EMPTY_LIST);
        
        verify(responder).respondWithLoginFirst();
        
        Assert.assertTrue(sessionIsValid);
    }

    @Test
    public void shouldRespondWithHelloWhenLoggedIn() throws Exception {
        String user = "Foo";

        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.isLoggedIn()).thenReturn(true);
        when(context.getUserName()).thenReturn(user);
        when(context.getResponder()).thenReturn(responder);

        boolean sessionIsValid = Commands.HELLO.execute(context, Collections.EMPTY_LIST);
        
        verify(responder).respondWithHelloToUser(user);

        Assert.assertTrue(sessionIsValid);
    }
}
