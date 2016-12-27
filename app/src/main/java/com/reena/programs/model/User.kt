package com.reena.programs.model

/**
 * Created by scispl15 on 27-12-2016.
 */

class User {
    var name: String = ""
    var email: String = ""

    constructor() {
    }

    constructor(name: String, email: String) {
        this.name = name
        this.email = email
    }
}
