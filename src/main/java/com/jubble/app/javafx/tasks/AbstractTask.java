package com.jubble.app.javafx.tasks;

import javafx.concurrent.Task;

public abstract class AbstractTask extends Task<Void> {
    /**
     * Returns name of a Task.
     * @return simpleName
     * */
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
