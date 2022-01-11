package ru.mrs.base.service.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public abstract class VfsAbstract {

    private final String root;

    /*public VfsAbstract(String root) {
        this.root = root;
    }*/

}
