package de.dbo.samples.tuprolog.tuprolog0;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;

/**
 * Logger to be used in the Prolog solver
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class SolverLogger {

    private static final Map<String, Integer> MESSAGE_TYPES = new HashMap<String, Integer>();
    static {
        MESSAGE_TYPES.put("err", new Integer(0));
        MESSAGE_TYPES.put("warn", new Integer(1));
        MESSAGE_TYPES.put("info", new Integer(3)); // 2 to enable
        MESSAGE_TYPES.put("debug", new Integer(3));
    }

    private final org.slf4j.Logger            log;

    public SolverLogger(final String name) {
        this.log = LoggerFactory.getLogger(name);
        log.debug("logger created. Name=" + this.log.getName());

    }

    public String getName() {
        return this.log.getName();
    }

    public void log(final String messageTerm) {
        log(Term.createTerm(messageTerm));
    }

    public void log(final Term messageTerm) {
        final Struct message = (Struct) messageTerm.getTerm();
        final int n = message.getArity();
        if (0 == n) {
            log.error(badFormat(message));
        }
        final Integer type = MESSAGE_TYPES.get(message.getName());
        if (null == type) {
            throw new RuntimeException(
                    badFormat("Can't determine message type", message));
        }

        // prepare printable message
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final Term contents = message.getArg(i).getTerm();
            contents.resolveTerm();
            if (contents.isEmptyList()) {
                sb.append("[]");
            }
            else if (contents.isAtom()) {
                sb.append(qClean(contents.toString()));
            }
            else if (contents instanceof Var) {
                final Var var = (Var) contents;
                sb.append("?" + var.getOriginalName());
            }
            else {
                sb.append(contents.getTerm().toString());
            }
        }

        // send to the logger
        final String txt = sb.toString();
        switch (type.intValue()) {
            case 0:
                log.error(txt);
                break;
            case 1:
                log.warn(txt);
                break;
            case 2:
                log.info(txt);
                break;
            case 3:
                log.debug(txt);
                break;
            default:
                new RuntimeException(
                        "Should never happen: type=" + type + " is not acceptable");
        }
    }

    /*
     * drop single-quotations (begin and end of a string), if any
     */
    private static final String Q = "'";

    private static final String qClean(final String qStr) {
        if (null != qStr && qStr.startsWith(Q) && qStr.endsWith(Q)) {
            return qStr.substring(1, qStr.length() - 1);
        }
        else {
            return qStr;
        }
    }

    private static final String badFormat(Term message) {
        return badFormat(null, message);
    }

    private static final String badFormat(String comment, Term message) {
        return (null == comment ? "" : comment + ". ")
                + "Bad format of the logger message: "
                + message + ". Expected \n" + messageTypes();
    }

    private static final String messageTypes() {
        final StringBuilder sb = new StringBuilder();
        for (final String name : MESSAGE_TYPES.keySet()) {
            sb.append(name + "(...) | ");
        }
        final String ret = sb.toString();
        return ret.substring(0, ret.length() - 2);
    }

}
