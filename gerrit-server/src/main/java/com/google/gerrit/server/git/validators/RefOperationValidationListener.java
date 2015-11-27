// Copyright (C) 2014 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.gerrit.server.git.validators;

import com.google.gerrit.extensions.annotations.ExtensionPoint;
import com.google.gerrit.server.events.RefReceivedEvent;
import com.google.gerrit.server.validators.ValidationException;

import java.util.List;

/**
 * Listener to provide validation on operation that is going to be performed on
 * given ref
 */
@ExtensionPoint
public interface RefOperationValidationListener {
  /**
   * Validate a ref operation before it is performed.
   *
   * @param refEvent ref operation specification
   * @return empty list or informational messages on success
   * @throws ValidationException if the ref operation fails to validate
   */
  List<ValidationMessage> onRefOperation(RefReceivedEvent refEvent)
      throws ValidationException;
}
