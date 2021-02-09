/* Project Euler solutions written by John Gaughan
 * Copyright (C) 2021  John Gaughan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package us.coffeecode.project_euler;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import us.coffeecode.project_euler.common.primes.FileCachePrimeProvider;
import us.coffeecode.project_euler.common.primes.IPrimeProvider;

/**
 * <p>
 * This class configures Spring beans for the whole project.
 * </p>
 * <p>
 * Copyright (c) 2021 John Gaughan
 * </p>
 *
 * @author John Gaughan &lt;john@coffeecode.us&gt;
 */
@Configuration
@ComponentScan
public class InjectionConfiguration {

  @Bean
  public IPrimeProvider getPrimeProvider() {
    IPrimeProvider primes = new FileCachePrimeProvider(Path.of("primes.bin"));
    try {
      // This pre-caches primes prior to running any test cases. This way none of them have severe lag from the amount
      // of time required to generate and cache the prime numbers.
      primes.getFirstNPrimes(1);
    }
    catch (RuntimeException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    return primes;
  }

}
