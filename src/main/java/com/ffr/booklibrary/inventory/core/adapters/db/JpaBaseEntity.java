package com.ffr.booklibrary.inventory.core.adapters.db;

import com.vladmihalcea.hibernate.type.json.JsonType;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@TypeDefs({@TypeDef(name = "json", typeClass = JsonType.class)})
@MappedSuperclass
public class JpaBaseEntity {}
