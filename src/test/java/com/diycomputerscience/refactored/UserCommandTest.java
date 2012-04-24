package com.diycomputerscience.refactored;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public final class UserCommandTest {
    @Test
    public void userCommandShouldRecordUserAndInvalidateSession() throws Exception {
        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.getResponder()).thenReturn(responder);

        boolean sessionIsValid = Commands.USER.execute(context, Arrays.asList(new String [] { "Foo" }));

        verify(context).setUserName("Foo");
        verify(context).invalidateLogin();
        verify(responder).respondWithOk();

        Assert.assertTrue(sessionIsValid);
    }

    @Test
    public void userCommandShouldAcceptOneParameter() {
        Assert.assertEquals(1, Commands.USER.getNumberOfArguments());
    }

    @Test
    public void verifyCommandName() {
        Assert.assertEquals("user", Commands.USER.getCommandName());
    }
}
