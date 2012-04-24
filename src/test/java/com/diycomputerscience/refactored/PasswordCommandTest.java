package com.diycomputerscience.refactored;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;
import static org.mockito.Mockito.*;

public final class PasswordCommandTest {
    @Test
    public void passwordCommandShouldAcceptOneParameter() {
        Assert.assertEquals(1, Commands.PASSWORD.getNumberOfArguments());
    }

    @Test
    public void verifyCommandName() {
        Assert.assertEquals("pass", Commands.PASSWORD.getCommandName());
    }

    @Test
    public void shouldRespondWithIssueUserCommandFirstWhenNotLoggedIn() throws Exception {
        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.getUserName()).thenReturn(null);
        when(context.getResponder()).thenReturn(responder);

        boolean sessionIsValid = Commands.PASSWORD.execute(context, Collections.EMPTY_LIST);

        verify(responder).respondWithIssueUserCommandFirst();
        verify(context, never()).login(any(String.class));

        Assert.assertTrue(sessionIsValid);
    }

    @Test
    public void shouldRespondWithUserLoggedInOnSuccessfullLogin() throws Exception {
        String user = "Foo";
        String password = "secret";

        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.getUserName()).thenReturn(user);
        when(context.getResponder()).thenReturn(responder);
        when(context.login(password)).thenReturn(true);

        boolean sessionIsValid = Commands.PASSWORD.execute(context, Arrays.asList( new String [] { password }));

        verify(context).login(password);
        verify(responder).respondWithUserSuccessfullyLoggedIn(user);

        Assert.assertTrue(sessionIsValid);
    }

    @Test
    public void shouldRespondWithIncorrectPasswordOnFailedLogin() throws Exception {
        String user = "Foo";
        String password = "secret";

        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.getUserName()).thenReturn(user);
        when(context.getResponder()).thenReturn(responder);
        when(context.login(password)).thenReturn(false);

        boolean sessionIsValid = Commands.PASSWORD.execute(context, Arrays.asList( new String [] { password }));

        verify(context).login(password);
        verify(responder).respondWithInvalidPassword();

        Assert.assertTrue(sessionIsValid);
    }
}
