/**
 * Copyright 2017 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.v3.oas.models.media;

import java.util.Map;
import java.util.Objects;

/**
 * ArraySchema
 */

public class ArraySchema extends Schema<Object> {
    private Schema<?> items = null;
    private Map<String, Object> contains = null;
    private Boolean additionalItems = false;
    private Boolean unevaluatedItems = false;
    private Boolean uniqueItems = false;
    private Integer minContains = null;
    private Integer maxContains = null;

    public ArraySchema() {
        super("array", null);
    }

    @Override
    public ArraySchema type(String type) {
        super.setType(type);
        return this;
    }

    /**
     * returns the items property from a ArraySchema instance.
     *
     * @return Schema items
     **/

    public Schema<?> getItems() {
        return items;
    }

    public void setItems(Schema<?> items) {
        this.items = items;
    }

    public ArraySchema items(Schema<?> items) {
        this.items = items;
        return this;
    }

    public Map<String, Object> getContains() {
        return contains;
    }

    public void setContains(Map<String, Object> contains) {
        this.contains = contains;
    }

    public ArraySchema contains(Map<String, Object> contains) {
        this.contains = contains;
        return this;
    }

    public Boolean getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(Boolean additionalItems) {
        this.additionalItems = additionalItems;
    }

    public ArraySchema additionalItems(Boolean additionalItems) {
        this.additionalItems = additionalItems;
        return this;
    }

    public Boolean getUnevaluatedItems() {
        return unevaluatedItems;
    }

    public void setUnevaluatedItems(Boolean unevaluatedItems) {
        this.unevaluatedItems = unevaluatedItems;
    }

    public ArraySchema unevaluatedItems(Boolean unevaluatedItems) {
        this.unevaluatedItems = unevaluatedItems;
        return this;
    }

    @Override
    public Boolean getUniqueItems() {
        return uniqueItems;
    }

    @Override
    public void setUniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    @Override
    public ArraySchema uniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
        return this;
    }

    public Integer getMinContains() {
        return minContains;
    }

    public void setMinContains(Integer minContains) {
        this.minContains = minContains;
    }

    public Schema minContains(Integer minContains) {
        this.minContains = minContains;
        return this;
    }

    public Integer getMaxContains() {
        return maxContains;
    }

    public void setMaxContains(Integer maxContains) {
        this.maxContains = maxContains;
    }

    public Schema maxContains(Integer maxContains) {
        this.maxContains = maxContains;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArraySchema arraySchema = (ArraySchema) o;
        return Objects.equals(this.items, arraySchema.items) &&
                Objects.equals(this.contains, arraySchema.contains) &&
                Objects.equals(this.additionalItems, arraySchema.additionalItems) &&
                Objects.equals(this.unevaluatedItems, arraySchema.unevaluatedItems) &&
                Objects.equals(this.uniqueItems, arraySchema.uniqueItems) &&
                Objects.equals(this.minContains, arraySchema.minContains) &&
                Objects.equals(this.maxContains, arraySchema.maxContains) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ArraySchema {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    items: ").append(toIndentedString(items)).append("\n");
        sb.append("    contains: ").append(toIndentedString(contains)).append("\n");
        sb.append("    additionalItems: ").append(toIndentedString(additionalItems)).append("\n");
        sb.append("    unevaluatedItems: ").append(toIndentedString(unevaluatedItems)).append("\n");
        sb.append("    uniqueItems: ").append(toIndentedString(uniqueItems)).append("\n");
        sb.append("    minContains: ").append(toIndentedString(minContains)).append("\n");
        sb.append("    maxContains: ").append(toIndentedString(maxContains)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
