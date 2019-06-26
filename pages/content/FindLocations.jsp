<%@ include file="../common/moduleInclude.jsp" %>


<td valign="top" align="center">

    <table id="findLocs" cellpadding="0" cellspacing="0" border="0" align="left" valign="top">
        <tr align="left">
            <td>
                <br/>
                <b>Find ATM Locations</b>
            </td>
            <td width="20">
            </td>
            <td>
                <br/>
                <b>Find Branch Locations</b>
            </td>
        </tr>
        <tr>
            <td>
                <table id="atm" cellpadding="0" cellspacing="0" border="0" align="left" valign="top">
                    <s:form action="ShowLocations" method="POST" theme="simple">
                        <tr valign="middle" align="left" >

                            <td valign="top" align="left">
                                <s:label value="Address: " for="address" cssStyle="font-weight:normal"/>
                            </td>
                            <td align="left" valign="top">
                                <s:textfield name="address" id="address" cssStyle="width:140px" />
                                <br/><br/>
                            </td>
                        </tr>
                        <tr valign="middle">
                            <td valign="top" align="left">
                                <s:label value="City: " for="city" cssStyle="font-weight:normal"/>
                            </td>
                            <td  align="left" valign="top">
                                <s:textfield name="city" id="city" maxLength="50" cssStyle="width:140px"/>
                                <br/><br/>
                            </td>
                            <td valign="top" align="left">
                                <s:label value="State: " for="state"  cssStyle="font-weight:normal"/>
                            </td>
                            <td  align="left" valign="top">
                                <select name="state">
                                    <option value="">Choose a state</option>
                                    <option value="AK">AK</option>
                                    <option value="AL">AL</option>
                                    <option value="AR">AR</option>
                                    <option value="AZ">AZ</option>
                                    <option value="CA">CA</option>
                                    <option value="CO">CO</option>
                                    <option value="CT">CT</option>
                                    <option value="DC">DC</option>
                                    <option value="DE">DE</option>
                                    <option value="FL">FL</option>
                                    <option value="GA">GA</option>
                                    <option value="HI">HI</option>
                                    <option value="IA">IA</option>
                                    <option value="ID">ID</option>
                                    <option value="IL">IL</option>
                                    <option value="IN">IN</option>
                                    <option value="KS">KS</option>
                                    <option value="KY">KY</option>
                                    <option value="LA">LA</option>
                                    <option value="MA">MA</option>
                                    <option value="MD">MD</option>
                                    <option value="ME">ME</option>
                                    <option value="MI">MI</option>
                                    <option value="MN">MN</option>
                                    <option value="MO">MO</option>
                                    <option value="MS">MS</option>
                                    <option value="MT">MT</option>
                                    <option value="NC">NC</option>
                                    <option value="ND">ND</option>
                                    <option value="NE">NE</option>
                                    <option value="NH">NH</option>
                                    <option value="NJ">NJ</option>
                                    <option value="NM">NM</option>
                                    <option value="NY">NY</option>
                                    <option value="NV">NV</option>
                                    <option value="OH">OH</option>
                                    <option value="OK">OK</option>
                                    <option value="OR">OR</option>
                                    <option value="PA">PA</option>
                                    <option value="RI">RI</option>
                                    <option value="SC">SC</option>
                                    <option value="SD">SD</option>
                                    <option value="TN">TN</option>
                                    <option value="TX">TX</option>
                                    <option value="UT">UT</option>
                                    <option value="VA">VA</option>
                                    <option value="VT">VT</option>
                                    <option value="WA">WA</option>
                                    <option value="WV">WV</option>
                                    <option value="WI">WI</option>
                                    <option value="WY">WY</option>
                                </select>
                                <br/><br/>
                            </td>
                        </tr>
                        <tr align="center" valign="middle">
                            <td valign="middle">
                                <b>Or</b><br/><br/>
                            </td>
                        </tr>
                        <tr valign="middle">
                            <td valign="top" align="left">
                                <s:label value="Zip Code: " for="zip"  cssStyle="font-weight:normal"/>
                            </td>
                            <td  align="left" valign="top">
                                <s:textfield name="zip" id="zip" maxLength="70" cssStyle="width:140px"/>
                                <br/><br/>
                            </td>
                        </tr>
                        <tr valign="middle" align="right">
                            <td>
                            </td>
                            <td align = "left">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="submit" align="right" alt="Find" value="Find"/>
                                <br/><br/><br/>
                            </td>
                        </tr>
                        <s:hidden name="type" value="atm" />
                    </s:form>
                </table>
            </td>
            <td></td>  <!-- space -->
            <td>
                <table id="branch" cellpadding="0" cellspacing="0" border="0" align="left" valign="top" >
                    <s:form action="ShowLocations" method="POST" theme="simple">
                        <tr valign="middle" align="left" >
                            <br/><br/><br/><br/>
                            <td valign="top" align="left">
                                <s:label value="Address: " for="address" cssStyle="font-weight:normal"/>
                            </td>
                            <td align="left" valign="top">
                                <s:textfield name="address" id="address" cssStyle="width:140px" />
                                <br/><br/>
                            </td>
                        </tr>
                        <tr valign="middle">
                            <td valign="top" align="left">
                                <s:label value="City: " for="city" cssStyle="font-weight:normal"/>
                            </td>
                            <td  align="left" valign="top">
                                <s:textfield name="city" id="city" maxLength="50" cssStyle="width:140px"/>
                                <br/><br/>
                            </td>
                            <td valign="top" align="left">
                                <s:label value="State: " for="state"  cssStyle="font-weight:normal"/>
                            </td>
                            <td  align="left" valign="top">
                                <select name="state">
                                    <option value="">Choose a state</option>
                                    <option value="AK">AK</option>
                                    <option value="AL">AL</option>
                                    <option value="AR">AR</option>
                                    <option value="AZ">AZ</option>
                                    <option value="CA">CA</option>
                                    <option value="CO">CO</option>
                                    <option value="CT">CT</option>
                                    <option value="DC">DC</option>
                                    <option value="DE">DE</option>
                                    <option value="FL">FL</option>
                                    <option value="GA">GA</option>
                                    <option value="HI">HI</option>
                                    <option value="IA">IA</option>
                                    <option value="ID">ID</option>
                                    <option value="IL">IL</option>
                                    <option value="IN">IN</option>
                                    <option value="KS">KS</option>
                                    <option value="KY">KY</option>
                                    <option value="LA">LA</option>
                                    <option value="MA">MA</option>
                                    <option value="MD">MD</option>
                                    <option value="ME">ME</option>
                                    <option value="MI">MI</option>
                                    <option value="MN">MN</option>
                                    <option value="MO">MO</option>
                                    <option value="MS">MS</option>
                                    <option value="MT">MT</option>
                                    <option value="NC">NC</option>
                                    <option value="ND">ND</option>
                                    <option value="NE">NE</option>
                                    <option value="NH">NH</option>
                                    <option value="NJ">NJ</option>
                                    <option value="NM">NM</option>
                                    <option value="NY">NY</option>
                                    <option value="NV">NV</option>
                                    <option value="OH">OH</option>
                                    <option value="OK">OK</option>
                                    <option value="OR">OR</option>
                                    <option value="PA">PA</option>
                                    <option value="RI">RI</option>
                                    <option value="SC">SC</option>
                                    <option value="SD">SD</option>
                                    <option value="TN">TN</option>
                                    <option value="TX">TX</option>
                                    <option value="UT">UT</option>
                                    <option value="VA">VA</option>
                                    <option value="VT">VT</option>
                                    <option value="WA">WA</option>
                                    <option value="WV">WV</option>
                                    <option value="WI">WI</option>
                                    <option value="WY">WY</option>
                                </select>
                                <br/><br/>
                            </td>
                        </tr>
                        <tr align="center" valign="middle">
                            <td valign="middle">
                                <b>Or</b><br/><br/>
                            </td>
                        </tr>
                        <tr valign="middle">
                            <td valign="top" align="left">
                                <s:label value="Zip Code: " for="zip"  cssStyle="font-weight:normal"/>
                            </td>
                            <td  align="left" valign="top">
                                <s:textfield name="zip" id="zip" maxLength="70" cssStyle="width:140px"/>
                                <br/><br/>
                            </td>
                        </tr>
                        <tr valign="middle" align="right">
                            <td>
                            </td>
                            <td align = "left">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="submit" align="right" alt="Find" value="Find"/>
                                <br/><br/><br/><br/><br/><br/><br/>
                            </td>
                        </tr>
                        <s:hidden name="type" value="branch" />
                    </s:form>
                </table>
            </td>
        </tr>

    </table>
</td>

