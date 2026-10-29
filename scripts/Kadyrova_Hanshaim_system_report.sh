
#!/bin/bash

STUDENT_NAME="Kadyrova Hanshaim"
LOG_FILE="logs/Kadyrova_Hanshaim_system_report.log"

{
    echo "================================"
    echo "SYSTEM MONITORING REPORT"
    echo "Student: $STUDENT_NAME"
    echo "================================"

    echo ""
    echo "1. Current Date and Time:"
    date

    echo ""
    echo "2. Current Username:"
    whoami

    echo ""
    echo "3. Operating System:"
    cat /etc/os-release | head -n 5

    echo ""
    echo "4. Kernel Version:"
    uname -r

    echo ""
    echo "5. CPU Information:"
    lscpu | head -n 15

    echo ""
    echo "6. Memory Information:"
    free -h

    echo ""
    echo "7. Disk Usage:"
    df -h

    echo ""
    echo "8. Available Disk Space:"
    df -h /

    echo ""
    echo "Report completed successfully."
} | tee "$LOG_FILE"

