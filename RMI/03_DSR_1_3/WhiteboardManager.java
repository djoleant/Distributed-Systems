/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ 03 DSR primer Whiteboard bez Callbacka                                  │
  └─────────────────────────────────────────────────────────────────────────┘
*/

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;

public class WhiteboardManager extends UnicastRemoteObject implements IWhiteboardManager {

    private Vector<IShape> theList;
    private int version;

    protected WhiteboardManager() throws RemoteException {
        super();
        theList = new Vector<IShape>();
        version = 0;
    }

    @Override
    public IShape addNewShape(String aType, Rectangle anEnclosing, Color aLine, Color aFill, boolean anIsFilled)
            throws RemoteException {
        version++;
        IShape s = new Shape(aType, anEnclosing, aLine, aFill, anIsFilled);
        theList.addElement(s);
        return (IShape) s;
    }

    @Override
    public Vector<IShape> getAllShapes() throws RemoteException {
        return theList;
    }

    @Override
    public int getVersion() throws RemoteException {
        return version;
    }

}
