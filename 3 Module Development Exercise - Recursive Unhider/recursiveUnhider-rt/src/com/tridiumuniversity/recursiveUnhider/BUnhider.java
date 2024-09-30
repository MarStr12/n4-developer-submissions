package com.tridiumuniversity.recursiveUnhider;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BComponent;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Slot;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;
import java.util.logging.Logger;

@NiagaraType
@NiagaraAction(
        name = "unhide",
        flags = Flags.ASYNC
)
public class BUnhider extends BComponent {
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.recursiveUnhider.BUnhider(3895808588)1.0$ @*/
/* Generated Mon Sep 30 12:21:23 CEST 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Action "unhide"

  /**
   * Slot for the {@code unhide} action.
   * @see #unhide()
   */
  public static final Action unhide = newAction(Flags.ASYNC, null);

  /**
   * Invoke the {@code unhide} action.
   * @see #unhide
   */
  public void unhide() { invoke(unhide, null, null); }

  //endregion Action "unhide"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BUnhider.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public void doUnhide (Context context) {
      removeHiddenFlagFromChildren((BComponent) getParent(), context);
    }
    private void removeHiddenFlagFromChildren(BComponent component, Context context) {
      removeHiddenFlagFromSlots(component, context);
      BComponent[] childComponentsArr = component.getChildComponents();
      if (childComponentsArr.length == 0 ) {
        return;
      }
      for (BComponent c : childComponentsArr) {
        removeHiddenFlagFromChildren(c, context);
      }
    }
    private void removeHiddenFlagFromSlots (BComponent component, Context context) {
      for (Slot slot : component.getSlots()){
        if (Flags.isHidden(component, slot)){
          Flags.remove(component, slot, context, Flags.HIDDEN);
          logger.fine("Hidden Flag removed from: " + slot);
        }
      }
    }

    @Override
    public IFuture post (Action action, BValue value, Context context) {
      if (unhide.equals(action)) {
        Thread thread = new Thread(() -> doUnhide(context), "UnhiderThread");
        thread.start();
      }
      return null;
    }
    private static final Logger logger = Logger.getLogger("Unhider");
}